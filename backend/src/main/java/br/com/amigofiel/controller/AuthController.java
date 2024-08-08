package br.com.amigofiel.controller;

import br.com.amigofiel.domain.dto.AdoptantDTO;
import br.com.amigofiel.domain.dto.LoginDTO;
import br.com.amigofiel.domain.dto.UserDTO;
import br.com.amigofiel.domain.entities.Adoptant;
import br.com.amigofiel.domain.entities.User;
import br.com.amigofiel.infra.security.TokenService;
import br.com.amigofiel.repositories.AdoptantRepository;
import br.com.amigofiel.repositories.UserRepository;
import br.com.amigofiel.services.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("adoptants/auth")
@Tag(name = "User", description = "Endpoints relacionados a autenticação")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository authRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private TokenService tokenService;

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
}
