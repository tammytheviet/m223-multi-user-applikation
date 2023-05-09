package ch.wiss.pruefung_294_295.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Diese Klasse ist eine Entity Klasse. Sie wird in der Datenbank gespeichert.
 * @class Chapter
 * @author Fabio Facundo & Tam Lai Nguyen
 * @version 1.0
 * 
 * @Entity Eclipse erstellt eine Tabelle und eine Verbindung zu der Datenbank.
 * @Table Eclipse gibt Tabelle den Namen chapter.
 * @Id Deklariert, dass mangaId ein Primary Key ist.
 * @GeneratedValue Bestimmt die Primary Key strategy
 * @ManyToOne Bestimmt, dass Chapter eine Annotation zu einer anderen Tabelle hat.
 * @JoinColumn Macht ein JoinColumn zwischen Chapter und Manga. Man muss den Namen der Id angeben. Nullable macht, dass Chapter nicht null sein kann
 * @JsonBackReference Zeigt auf, dass Chapter eine child row ist. 
 * 
 */

@Entity
@Table(name = "chapter")
public class Chapter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int chapterId;
	private String inhalt;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id", nullable = false)
	@JsonBackReference
	private Manga manga;

	public Chapter(String inhalt, Manga manga) {
		this.inhalt = inhalt;
		this.manga = manga;
	}

	public Manga getManga() {
		return manga;
	}

	public void setManga(Manga manga) {
		this.manga = manga;
	}

	public String getInhalt() {
		return inhalt;
	}
	public void setInhalt(String inhalt) {
		this.inhalt = inhalt;
	}

}