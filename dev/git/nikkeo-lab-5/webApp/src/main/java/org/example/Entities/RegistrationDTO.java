package org.example.Entities;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationDTO {
    private String username;
    private String password;

    public RegistrationDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public RegistrationDTO() { }
}
