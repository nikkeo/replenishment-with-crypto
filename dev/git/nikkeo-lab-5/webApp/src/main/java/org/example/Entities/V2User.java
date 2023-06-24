package org.example.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
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
public class V2User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "userType")
    private V2Role v2Role;
    @OneToOne(mappedBy = "user")
    @JsonBackReference
    private V2Apartment apartment;

    public V2User (String username, String password){
        this.username = username;
        this.password = password;
        this.v2Role = new V2Role(UserTypes.ROLE_USER);
    }

    public V2User() {}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<V2Role> authorities = new ArrayList<>();
        authorities.add(v2Role);
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
