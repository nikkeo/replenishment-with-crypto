package org.example.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationDto {
    private String username;
    private String password;

    public RegistrationDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public RegistrationDto() { }
}
