package ch.wiss.pruefung_294_295.exceptions;

public class ChapterCouldNotBeSavedException extends RuntimeException {
	public ChapterCouldNotBeSavedException(String inhalt) {
		super("The chapter with name '" + inhalt + "' could not be saved.");
	}
}
