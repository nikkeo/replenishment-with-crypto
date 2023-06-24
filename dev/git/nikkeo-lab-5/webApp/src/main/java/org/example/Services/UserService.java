package org.example.Services;

import org.example.Entities.V2User;
import org.example.Repository.UserRepository;
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
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService{
    @Autowired
    private PasswordEncoder encoder;
    private UserRepository userRepository;

    public UserService(UserRepository repositories){
        this.userRepository = repositories;
    }

    public Iterable<V2User> getAllUsers() {
        return userRepository.findAll();
    }

    public V2User getUserById(int id) {
        return userRepository.findById(Long.valueOf(id)).orElse(null);
    }
    public V2User saveUser(V2User user) throws Exception {
        if (getUserById(user.getId()) != null)
            throw new Exception("User already exists");
        return userRepository.save(user);
    }

    public void deleteUserById(int id) {
        userRepository.deleteById(Long.valueOf(id));
    }

    public Optional<V2User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public V2User updateUser(V2User updatedUser) {
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
