package org.example.Entities;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
public class RoleV2 implements GrantedAuthority {
    public UserTypes userType;

    public RoleV2(UserTypes userType){
        this.userType = userType;
    }

    @Override
    public String getAuthority() {
        return userType.toString();
    }
}
