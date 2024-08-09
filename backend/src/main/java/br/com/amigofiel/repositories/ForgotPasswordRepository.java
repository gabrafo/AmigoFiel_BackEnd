package br.com.amigofiel.repositories;

import br.com.amigofiel.domain.entities.ForgotPassword;
import br.com.amigofiel.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword, Long> {

    @Query("SELECT fp FROM ForgotPassword fp WHERE fp.otp = ?1 and fp.user = ?2")
    Optional<ForgotPassword> findByOtpAndUser(int otp, User user);
}
