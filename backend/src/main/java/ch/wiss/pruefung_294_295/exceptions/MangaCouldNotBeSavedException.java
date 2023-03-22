package ch.wiss.pruefung_294_295.exceptions;

public class MangaCouldNotBeSavedException extends RuntimeException {
	public MangaCouldNotBeSavedException(String titel) {
		super("The manga with name '" + titel + "' could not be saved.");
	}
}
