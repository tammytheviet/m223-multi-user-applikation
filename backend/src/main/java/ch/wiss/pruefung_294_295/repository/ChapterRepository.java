package ch.wiss.pruefung_294_295.repository;

import org.springframework.data.repository.CrudRepository;

import ch.wiss.pruefung_294_295.model.Chapter;

/**
 * Dieses Interface wird verwendet, um die Datenbankabfragen zu machen.
 * @class ChapterRepository
 * @author Fabio Facundo & Tam Lai Nguyen
 * @version 1.0
 */
public interface ChapterRepository extends CrudRepository < Chapter, Integer > {
    Chapter deleteAllByMangaId(int id);

}