package org.example.Services;

import org.example.Repository.UserRepository;
import org.example.Entities.UserV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService{
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    @Autowired
    public UserService(PasswordEncoder encoder, UserRepository repositories){
        this.encoder = encoder;
        this.userRepository = repositories;
    }

    public Iterable<UserV2> getAllUsers() {
        return userRepository.findAll();
    }

    public UserV2 getUserById(int id) {
        return userRepository.findById(Long.valueOf(id)).orElse(null);
    }
    public UserV2 saveUser(UserV2 user) throws Exception {
        if (getUserById(user.getId()) != null)
            throw new Exception("User already exists");
        return userRepository.save(user);
    }

    public void deleteUserById(int id) {
        userRepository.deleteById(Long.valueOf(id));
    }

    public Optional<UserV2> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public UserV2 updateUser(UserV2 updatedUser) {
        return userRepository.save(updatedUser);
    }
    @Bean
    private AuthenticationManager authenticationManager(UserDetailsService userDetailsService){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        return new ProviderManager(provider);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Not valid user"));
    }
}
