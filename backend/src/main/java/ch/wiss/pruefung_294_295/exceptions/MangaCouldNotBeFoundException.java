package ch.wiss.pruefung_294_295.exceptions;

public class MangaCouldNotBeFoundException extends RuntimeException {
	public MangaCouldNotBeFoundException(Integer id) {
		super("The manga with the name '" + id + "' could not be found.");
	}
}