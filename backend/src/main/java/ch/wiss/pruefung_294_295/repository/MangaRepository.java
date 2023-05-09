package ch.wiss.pruefung_294_295.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import ch.wiss.pruefung_294_295.model.Manga;

/**
 * Dieses Interface wird verwendet, um die Datenbankabfragen zu machen.
 * @class MangaRepository
 * @author Fabio Facundo & Tam Lai Nguyen
 * @version 1.0
 */
public interface MangaRepository extends CrudRepository < Manga, Integer > {
	public Manga deleteById(int id);

	public Optional<Manga> findByTitel(String Titel);
}