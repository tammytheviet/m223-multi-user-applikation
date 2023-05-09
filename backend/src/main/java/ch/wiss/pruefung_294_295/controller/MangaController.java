package ch.wiss.pruefung_294_295.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ch.wiss.pruefung_294_295.exceptions.MangaCouldNotBeDeletedException;
import ch.wiss.pruefung_294_295.exceptions.MangaCouldNotBeSavedException;
import ch.wiss.pruefung_294_295.exceptions.MangaCouldNotBeUpdatedException;
import ch.wiss.pruefung_294_295.exceptions.MangaLoadException;
import ch.wiss.pruefung_294_295.model.Manga;
import ch.wiss.pruefung_294_295.repository.MangaRepository;

/**
 * Diese Klasse wird verwendet um die REST-API für die Mangas zu definieren.
 * 
 * @class MangaController
 * @author Fabio Facundo & Tam Lai Nguyen
 * @version 1.0
 * 
 * @param mangaRepository: MangaRepository
 * @param manga: Manga
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/manga")
@Transactional
public class MangaController 
{
	//Objekt von der Klasse MangaRepository 'mangaRepository'
	@Autowired
	private MangaRepository mangaRepository;

	/**
	 * Erstellt einen neuen Manga
	 * 
	 * @param newManga
	 * 
	 * @return ResponseEntity
	 */
	@PostMapping(path = "")
	public ResponseEntity<String> addNewManga(@Valid @RequestBody Manga newManga) 
	{
		//Speichert den neuen Manga in der Datenbank
		try 
		{
			mangaRepository.save(newManga);
		}
		catch (Exception ex) 
		{
			throw new MangaCouldNotBeSavedException(newManga.getTitel());
		}
		return ResponseEntity.ok("Saved");
	}
		
	/**
	 * Zeigt alle Mangas an
	 * 
	 * @return ResponseEntity
	 */
	@GetMapping(path = "")
	public ResponseEntity<Iterable<Manga>> getAllMangas() 
	{
		Iterable<Manga> mangas = null;

		//Lädt alle Mangas aus der Datenbank
		try 
		{
			mangas = mangaRepository.findAll();
		} 
		catch (Exception ex) 
		{
			throw new MangaLoadException();
		}
		return ResponseEntity.ok(mangas);
	}
	
	/**
	 * Löscht einen Manga
	 * 
	 * @param id
	 * 
	 * @return ResponseEntity
	 */
	@DeleteMapping(path = "")
    public ResponseEntity<String> deleteManga(@RequestParam int id)
    {

		//Löscht den Manga aus der Datenbank
        try
        {
            mangaRepository.deleteById(id);
        } 
        catch (Exception e) 
        {
            throw new MangaCouldNotBeDeletedException(id);
        }
        return ResponseEntity.ok("Deleted");
    }
	
	/**
	 * Aktualisiert einen Manga
	 * 
	 * @param id
	 * 
	 * @param mangaInfos
	 * 
	 * @return ResponseEntity
	 */
	@PutMapping(path = "/{id}") // UPDATE ONLY Request
    public @ResponseBody ResponseEntity<String> updateManga(@PathVariable (value = "id") int id, @RequestBody Manga mangaInfos) 
	{
		//Lädt den Manga aus der Datenbank
		Manga manga = mangaRepository.findById(id).get();

		//Aktualisiert den Manga in der Datenbank
        try 
		{
			manga.setTitel(mangaInfos.getTitel());
			manga.setGenre(mangaInfos.getGenre());
			manga.setZeichner(mangaInfos.getZeichner());
			manga.setAutor(mangaInfos.getAutor());
			manga.setStatus(mangaInfos.getStatus());
			manga.setVeröffentlichungsdatum(mangaInfos.getVeröffentlichungsdatum());
			mangaRepository.save(manga);
        } 
		catch (Exception e) 
		{
            throw new MangaCouldNotBeUpdatedException(manga.getTitel());
        }
		return ResponseEntity.ok("Updated");
	}
}
