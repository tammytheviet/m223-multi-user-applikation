package ch.wiss.pruefung_294_295.exceptions;

public class MangaCouldNotBeDeletedException extends RuntimeException {
	public MangaCouldNotBeDeletedException(int id) {
		super("The manga with the id '" + id + "' could not be deleted.");
	}
}
