package br.com.amigofiel.services;

import br.com.amigofiel.domain.entities.Adoptant;
import br.com.amigofiel.domain.entities.User;
import br.com.amigofiel.repositories.AdoptantRepository;
import br.com.amigofiel.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository authRepository;

    @Autowired
    AdoptantRepository adoptantRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return authRepository.findByUsername(username);
    }

    @Transactional
    public void registerAdoptant(Adoptant adoptant, User user) {
        if (user.getId() == null) {
            authRepository.save(user); // Save User if it is new
        }
        adoptant.setUser(user); // Set the User entity
        adoptantRepository.save(adoptant); // Save Adoptant entity
    }
}
