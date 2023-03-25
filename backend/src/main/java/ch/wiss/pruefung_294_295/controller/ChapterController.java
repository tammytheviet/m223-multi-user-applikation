package ch.wiss.pruefung_294_295.controller;

import java.util.Optional;

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

import ch.wiss.pruefung_294_295.exceptions.ChapterCouldNotBeDeletedException;
import ch.wiss.pruefung_294_295.exceptions.ChapterCouldNotBeSavedException;
import ch.wiss.pruefung_294_295.exceptions.ChapterCouldNotBeUpdatedException;
import ch.wiss.pruefung_294_295.exceptions.ChapterLoadException;
import ch.wiss.pruefung_294_295.exceptions.MangaCouldNotBeFoundException;
import ch.wiss.pruefung_294_295.model.Chapter;
import ch.wiss.pruefung_294_295.model.Manga;
import ch.wiss.pruefung_294_295.repository.ChapterRepository;
import ch.wiss.pruefung_294_295.repository.MangaRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController // This means that this class is a Controller
@RequestMapping(path = "/chapter") // This means URL's start with /manga (after Application path)
public class ChapterController {

    /**
     * Wire Chapter und Manga Repositories
     */
	@Autowired
	private ChapterRepository chapterRepository;
	@Autowired
	private MangaRepository mangaRepository;

    /**
     * Creates new Chapter
     * 
     * @param mangaId
     * @param chapter
     * @return "saved"
     */
	@PostMapping(path = "/{manga}") // Map ONLY POST Requests
    public ResponseEntity<String> addNewChapter(@PathVariable(value = "manga") Integer mangaId,
                                             @Valid @RequestBody Chapter chapter)
    {
        Optional<Manga> manga = mangaRepository.findById(mangaId);

        if (manga.isEmpty()) 
        {
            throw new MangaCouldNotBeFoundException(mangaId);
        }

        chapter.setManga(manga.get());

        try {
            chapterRepository.save(chapter);
        } catch (Exception ex) {
            throw new ChapterCouldNotBeSavedException(chapter.getInhalt());
        }

        return ResponseEntity.ok("Saved");
    }
		
    /**
     * Lists all chapters
     * 
     * @return all chapters
     */
	@GetMapping(path = "")
	public ResponseEntity<Iterable<Chapter>> getAllChapters() {
		Iterable<Chapter> chapters = null;

		try {
			chapters = chapterRepository.findAll();
			
		} catch (Exception ex) {
			throw new ChapterLoadException();
		}

		return ResponseEntity.ok(chapters);
	}
	
	/**
     * Deletes specific chapter
     * 
     * @param id
     * @return "deleted"
     */
	@DeleteMapping(path = "") //DELETE ONLY Request
    public ResponseEntity<String> deleteChapter(@RequestParam int id)
    {
        try 
        {
            chapterRepository.deleteById(id);
        } 
        catch (Exception e) 
        {
            throw new ChapterCouldNotBeDeletedException(id);
        }

        return ResponseEntity.ok("Deleted");

    }
	
	/**
     * Updates specific chapter
     * 
     * @param id
     * @param chapterInfos
     * @return "updated"
     */
	@PutMapping(path = "/{id}") // UPDATE ONLY Request
    public @ResponseBody ResponseEntity<String> updateChapter(@PathVariable (value = "id") Integer id, @RequestBody Chapter chapterInfos) 
	{

		Chapter chapter = chapterRepository.findById(id).get();

        try 
		{
			chapter.setInhalt(chapterInfos.getInhalt());
			chapterRepository.save(chapter);
        } 
		catch (Exception e) 
		{
            throw new ChapterCouldNotBeUpdatedException(chapter.getInhalt());
        }

		return ResponseEntity.ok("Updated");

	}
}
