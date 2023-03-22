package ch.wiss.pruefung_294_295.exceptions;

public class MangaLoadException extends RuntimeException {
	public MangaLoadException() {
		super("Mangas could not be loaded.");
	}
}

