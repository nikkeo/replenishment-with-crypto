package org.example.Controllers;

import jakarta.transaction.Transactional;
import org.example.DTO.RegistrationDto;
import org.example.Entities.UserV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Transactional
@RequestMapping("/auth")
public class RegistrationController {
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UserController userController;

    @PostMapping("/registration")
    public UserV2 registrateUser(@RequestBody RegistrationDto registrationDTO) throws Exception {

       if (!userController.getUserByUsername(registrationDTO.getUsername()).isEmpty()){
           throw new Exception("Username was already claimed");
       }
       return userController.saveUser(new UserV2(registrationDTO.getUsername(), encoder.encode(registrationDTO.getPassword())));
    }
}


