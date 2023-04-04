package ch.wiss.pruefung_294_295.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
 * @param id: ID der Rolle
 * @param name: Name der Rolle
 */

@Entity
@Table(name = "role")
public class Role {

    @Id
    @OnDelete(action = OnDeleteAction.CASCADE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    public Role() {}

    public Role(ERole name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }

}