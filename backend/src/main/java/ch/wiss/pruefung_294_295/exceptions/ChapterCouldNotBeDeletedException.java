package ch.wiss.pruefung_294_295.exceptions;

public class ChapterCouldNotBeDeletedException extends RuntimeException {
	public ChapterCouldNotBeDeletedException(int chapterId) {
		super("The chapter with id '" + chapterId + "' could not be deleted.");
	}
}
