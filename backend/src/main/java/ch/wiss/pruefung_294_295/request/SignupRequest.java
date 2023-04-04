package ch.wiss.pruefung_294_295.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Diese Klasse wird vewendet, um die Anmeldung und Registrierung zu ermöglichen.
 * @class SignupRequest
 * @author Fabio Facundo & Tam Lai Nguyen
 * @version 1.0
 * 
 * @param username: Der Benutzername des Benutzers.
 * @param email: Die E-Mail des Benutzers.
 * @param role: Die Rolle des Benutzers.
 * @param password: Das Passwort des Benutzers.
 */
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }
}