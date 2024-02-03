package com.example.auth.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "utilisateur")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Utilisateur implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    private String mail;
    private boolean actif = false;
    @OneToOne(cascade = CascadeType.ALL)
    private Role role;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_"+ this.role.getTypeRole()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.actif;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.actif;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.actif;
    }

    @Override
    public boolean isEnabled() {
        return this.actif;
    }
}
