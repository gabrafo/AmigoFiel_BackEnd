package br.com.amigofiel.controller;

import br.com.amigofiel.domain.dto.AdoptantDTO;
import br.com.amigofiel.domain.dto.LoginDTO;
import br.com.amigofiel.domain.dto.MailBody;
import br.com.amigofiel.domain.dto.PasswordDTO;
import br.com.amigofiel.domain.entities.Adoptant;
import br.com.amigofiel.domain.entities.ForgotPassword;
import br.com.amigofiel.domain.entities.User;
import br.com.amigofiel.exceptions.NotFoundException;
import br.com.amigofiel.infra.security.TokenService;
import br.com.amigofiel.repositories.ForgotPasswordRepository;
import br.com.amigofiel.repositories.UserRepository;
import br.com.amigofiel.services.AuthService;
import br.com.amigofiel.services.EmailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

@RestController
@RequestMapping("auth")
@Tag(name = "User", description = "Endpoints relacionados a autenticação")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository authRepository;
    private final AuthService authService;
    private final TokenService tokenService;
    private final EmailService emailService;
    private final ForgotPasswordRepository forgotPasswordRepository;

    public AuthController(AuthenticationManager authenticationManager, UserRepository authRepository,
                          AuthService authService, TokenService tokenService, EmailService emailService,
                          ForgotPasswordRepository forgotPasswordRepository) {
        this.authenticationManager = authenticationManager;
        this.authRepository = authRepository;
        this.authService = authService;
        this.tokenService = tokenService;
        this.emailService = emailService;
        this.forgotPasswordRepository = forgotPasswordRepository;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(token);
    }

    @Transactional
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid AdoptantDTO data){
        if(this.authRepository.findByUsername(data.user().getUsername()) != null)
            return ResponseEntity.badRequest().body("Username already exists");
        if (this.authRepository.findByEmail(data.user().getEmail()) != null)
            return ResponseEntity.badRequest().body("Email already exists");

        Adoptant newAdoptant = new Adoptant(data);

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.user().getPassword());
        User newUser = new User(newAdoptant, data.user().getEmail(), data.user().getUsername(), encryptedPassword, data.user().getRole());

        authService.registerAdoptant(newAdoptant, newUser);

        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.getId())
                .toUri()).body(newAdoptant);
    }

    @PostMapping("/verify/{email}")
    public ResponseEntity<String> verifyMail(@PathVariable String email) {
        User user = userExists(email);

        LocalDateTime expirationDateTime = LocalDateTime.now().plusMinutes(5);
        ZonedDateTime zonedDateTime = expirationDateTime.atZone(ZoneId.systemDefault());
        Instant expirationTime = zonedDateTime.toInstant();
        Date expirationDate = Date.from(expirationTime);

        int otp = otpGenerator();

        MailBody mailBody = MailBody.builder().to(email).text("Esse é o código de segurança para sua recuperação " +
                        "de senha: " + otp)
                .subject("Recuperação de senha")
                .build();

        ForgotPassword fp = ForgotPassword.builder().otp(otp).expirationTime(expirationDate).user(user)
                .build();

        emailService.sendSimpleMessage(mailBody);
        forgotPasswordRepository.save(fp);

        return ResponseEntity.ok("Email enviado com sucesso.");
    }

    @PostMapping("/verify/{otp}/{email}")
    public ResponseEntity<String> verifyOtp(@PathVariable int otp, @PathVariable String email){
        User user = userExists(email);

        ForgotPassword fp = forgotPasswordRepository.findByOtpAndUser(otp, user).orElseThrow(()
                -> new NotFoundException("Invalid OTP for email: " + email));

        if(fp.getExpirationTime().before(Date.from(Instant.now()))){
            forgotPasswordRepository.delete(fp);
            return new ResponseEntity<>("Código de segurança expirado!", HttpStatus.EXPECTATION_FAILED);
        }

        String resetToken = tokenService.generateToken(user);

        return ResponseEntity.ok(resetToken);
    }

    @PutMapping("/change-password/{email}")
    public ResponseEntity<String> changePasswordHandler(@RequestBody PasswordDTO passwordDTO,
                                                        @PathVariable String email){

        if(!Objects.equals(passwordDTO.password(), passwordDTO.repeatPassword())) {
            return new ResponseEntity<>("As senhas não coincidem", HttpStatus.BAD_REQUEST);
        }
        User user = userExists(email);

        String encodedPassword = new BCryptPasswordEncoder().encode(passwordDTO.password());
        user.setPassword(encodedPassword);
        authRepository.save(user);

        return ResponseEntity.ok("Senha atualizada com sucesso");
    }

    private int otpGenerator(){
        Random rand = new Random();
        return rand.nextInt(100_000, 999_999);
    }

    private User userExists(String email) {
        User user = authRepository.findByEmail(email);
        if(user == null) {
            throw new NotFoundException("Please provide an valid email");
        }
        return user;
    }
}
