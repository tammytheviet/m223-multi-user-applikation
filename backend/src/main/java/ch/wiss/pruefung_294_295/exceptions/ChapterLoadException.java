package ch.wiss.pruefung_294_295.exceptions;

public class ChapterLoadException extends RuntimeException {
	public ChapterLoadException() {
		super("Chapters could not be loaded.");
	}
}

