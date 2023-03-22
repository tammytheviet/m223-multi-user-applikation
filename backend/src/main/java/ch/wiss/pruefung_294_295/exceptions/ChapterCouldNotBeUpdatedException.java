package ch.wiss.pruefung_294_295.exceptions;

public class ChapterCouldNotBeUpdatedException extends RuntimeException {
	public ChapterCouldNotBeUpdatedException(String inhalt) {
		super("The chapter with the name '" + inhalt + "' could not be updated.");
	}
}