package ch.wiss.pruefung_294_295.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * Diese Klasse ist eine Entity Klasse. Sie wird in der Datenbank gespeichert.
 * Diese Klasse wird verwendet, um die User zu speichern.
 * @class Chapter
 * @author Fabio Facundo & Tam Lai Nguyen
 * @version 1.0
 * 
 * @Entity Eclipse erstellt eine Tabelle und eine Verbindung zu der Datenbank.
 * @Table Eclipse gibt Tabelle den Namen chapter.
 * @Id Deklariert, dass mangaId ein Primary Key ist.
 * @GeneratedValue Bestimmt die Primary Key strategy
 * @Enumerated Bestimmt, dass es von ENUM zum STRING werden kann.
 * @Column Macht ein Column
 * 
 * @param id: ID des Benutzers
 * @param username: Benutzername des Benutzers
 * @param email: Email des Benutzers
 * @param password: Passwort des Benutzers
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @OnDelete(action = OnDeleteAction.CASCADE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String username;

    @NotNull
    private String email;

    @NotNull
    private String password;

    public User() {
        
    }

    public User(int id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @ManyToMany(fetch = FetchType.LAZY) //das ist der spannende ORM Teil: automatisches Mapping von M-N Beziehungen :-) 
    @JoinTable(name = "user_role",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set < Role > roles = new HashSet < > ();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Set < Role > getRoles() {
        return roles;
    }

    public void setRoles(Set < Role > roles) {
        this.roles = roles;
    }


}