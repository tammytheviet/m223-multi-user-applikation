package ch.wiss.pruefung_294_295.service;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.wiss.pruefung_294_295.model.User;

import java.util.List;

/**
 * Diese Klasse wird verwendet, um die Datenbankabfragen zu machen.
 * @author Fabio Facundo & Tam Lai Nguyen
 * @version 1.0
 * 
 * @param id: Die ID des Users
 * @param username: Der Username des Users
 * @param email: Die Email des Users
 * @param password: Das Passwort des Users
 */
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1;
    private Long id;
    private String username;
    private String email;

    @JsonIgnore
    private String password;

    private Collection <? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String username, String email, String password,
        Collection <? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority(role.getName().toString()))
            .collect(Collectors.toList());

        return new UserDetailsImpl(
            (long) user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getPassword(),
            authorities);
    }

    @Override
    public Collection < ? extends GrantedAuthority > getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}