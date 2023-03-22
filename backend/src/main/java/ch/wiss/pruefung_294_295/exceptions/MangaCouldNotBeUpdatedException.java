package ch.wiss.pruefung_294_295.exceptions;

public class MangaCouldNotBeUpdatedException extends RuntimeException {
	public MangaCouldNotBeUpdatedException(String titel) {
		super("The manga with the name '" + titel + "' could not be updated.");
	}
}