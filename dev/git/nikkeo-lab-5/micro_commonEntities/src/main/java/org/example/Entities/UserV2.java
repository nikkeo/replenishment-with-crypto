package org.example.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "AppUser")
@Getter @Setter
@NonNull
public class UserV2 implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "userType")
    private RoleV2 roleV2;
    @OneToOne(mappedBy = "user")
    @JsonBackReference
    private ApartmentV2 apartment;

    public UserV2(String username, String password){
        this.username = username;
        this.password = password;
        this.roleV2 = new RoleV2(UserTypes.ROLE_USER);
    }

    public UserV2() {}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<RoleV2> authorities = new ArrayList<>();
        authorities.add(roleV2);
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getPasswordConfirm() { return password; }
}
