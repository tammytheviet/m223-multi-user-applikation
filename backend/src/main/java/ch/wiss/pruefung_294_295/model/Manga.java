package ch.wiss.pruefung_294_295.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * @class Manga
 * @author Fabio Facundo
 * @version 1.0
 * 
 * @Entity Eclipse erstellt eine Tabelle und eine Verbindung zu der Datenbank.
 * @Table Eclipse gibt Tabelle den Namen manga.
 * @Id Deklariert, dass id ein Primary Key ist.
 * @GeneratedValue Bestimmt die Primary Key strategy
 * @OneToMany Bestimmt, dass Manga eine Annotation zu einer anderen Tabelle hat.
 * @JoinColumn Macht ein JoinColumn zwischen Manga und Chapter. Man muss auch noch den Namen der Id eingeben und zudem auch den Namen der Id in der anderen Tabelle
 * 
 */

@Entity
@Table(name = "manga")
public class Manga {

	@Id
	@OnDelete(action = OnDeleteAction.CASCADE)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String titel;

	private String genre;

	private String zeichner;

	private String autor;

	private String status;

	private Date veröffentlichungsdatum;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getZeichner() {
		return zeichner;
	}

	public void setZeichner(String zeichner) {
		this.zeichner = zeichner;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getVeröffentlichungsdatum() {
		return veröffentlichungsdatum;
	}

	public void setVeröffentlichungsdatum(Date veröffentlichungsdatum) {
		this.veröffentlichungsdatum = veröffentlichungsdatum;
	}
}