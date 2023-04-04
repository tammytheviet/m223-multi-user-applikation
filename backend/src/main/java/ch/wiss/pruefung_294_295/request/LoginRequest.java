package ch.wiss.pruefung_294_295.request;

import javax.validation.constraints.NotNull;

/**
 * Diese Klasse wird vewendet, um die Anmeldung und Registrierung zu ermöglichen.
 * @class LoginRequest
 * @author Fabio Facundo & Tam Lai Nguyen
 * @version 1.0
 * 
 * @param username: Der Benutzername des Benutzers.
 * @param password: Das Passwort des Benutzers.
 */
public class LoginRequest {
    @NotNull
    private String username;

    @NotNull
    private String password;

    public LoginRequest() {
        
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}