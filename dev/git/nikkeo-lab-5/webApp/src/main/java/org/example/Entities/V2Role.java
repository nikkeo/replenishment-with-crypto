package org.example.Entities;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
public class V2Role implements GrantedAuthority {
    public UserTypes userType;

    public V2Role (UserTypes userType){
        this.userType = userType;
    }

    @Override
    public String getAuthority() {
        return userType.toString();
    }
}
