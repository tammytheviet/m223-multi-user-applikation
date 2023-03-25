package ch.wiss.pruefung_294_295.controller;

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

@CrossOrigin(origins = "http://localhost:3000")
@RestController // This means that this class is a Controller
@RequestMapping(path = "/manga") // This means URL's start with /manga (after Application path)
public class MangaController {
	@Autowired
	private MangaRepository mangaRepository;

	@PostMapping(path = "") // Map ONLY Post Requests
	public ResponseEntity<String> addNewManga(@Valid @RequestBody Manga newManga) {
		try {
			mangaRepository.save(newManga);
		} catch (Exception ex) {
			throw new MangaCouldNotBeSavedException(newManga.getTitel());
		}

		return ResponseEntity.ok("Saved");
	}
		

	@GetMapping(path = "")
	public ResponseEntity<Iterable<Manga>> getAllMangas() {
		Iterable<Manga> mangas = null;

		try {
			mangas = mangaRepository.findAll();
		} catch (Exception ex) {
			throw new MangaLoadException();
		}

		return ResponseEntity.ok(mangas);
	}
	
	
	@DeleteMapping(path = "") //DELETE ONLY Request
    public ResponseEntity<String> deleteManga(@RequestParam int id)
    {
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
	
	
	@PutMapping(path = "/{id}") // UPDATE ONLY Request
    public @ResponseBody ResponseEntity<String> updateManga(@PathVariable (value = "id") int id, @RequestBody Manga mangaInfos) 
	{

		Manga manga = mangaRepository.findById(id).get();

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
