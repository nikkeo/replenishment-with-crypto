package org.example.Controllers;

import jakarta.transaction.Transactional;
import org.example.Entities.RegistrationDTO;
import org.example.Entities.V2User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Transactional
@RequestMapping("/auth")
public class RegistrationController {
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UserController userController;

    @PostMapping("/registration")
    public V2User registrateUser(@RequestBody RegistrationDTO registrationDTO) throws Exception {

       if (!userController.getUserByUsername(registrationDTO.getUsername()).isEmpty()){
           throw new Exception("Username was already claimed");
       }
       return userController.saveUser(new V2User(registrationDTO.getUsername(), encoder.encode(registrationDTO.getPassword())));
    }
}


