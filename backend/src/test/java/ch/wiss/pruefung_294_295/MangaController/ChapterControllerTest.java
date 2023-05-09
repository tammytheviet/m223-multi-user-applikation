package ch.wiss.pruefung_294_295.MangaController;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import ch.wiss.pruefung_294_295.controller.ChapterController;
import ch.wiss.pruefung_294_295.controller.MangaController;
import ch.wiss.pruefung_294_295.model.Chapter;
import ch.wiss.pruefung_294_295.model.Manga;
import ch.wiss.pruefung_294_295.repository.ChapterRepository;
import ch.wiss.pruefung_294_295.repository.MangaRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

@SpringBootTest
@ActiveProfiles("test")
public class ChapterControllerTest {
	
	@MockBean private MangaRepository mangaRepository;
	@MockBean private ChapterRepository chapterRepository;
	
	@Mock MangaController mangaController;
	@Mock ChapterController chapterController;

	// testManga wird für alle Tests verwendet
	Manga testManga = new Manga(
		"Testname", 
		"Testgenre", 
		"Testzeichner", 
		"Testautor", 
		"Teststatus", 
		new Date(2019-03-23)
	);
	
	@Test
	public void whenChapterControllerInjected_thenNotNull() throws Exception {
		assertThat(chapterController).isNotNull(); //hier wird geprüft, ob unser SUT existiert
	}

	@Test
    public void testAddChapter() 
	{	
		// given
		final String testInhalt = "Testinhalt";
		mangaRepository.save(testManga);
		Chapter testChapter = new Chapter(testInhalt, testManga);

		// when
		chapterRepository.save(testChapter);

		// then
		Optional<Chapter> result = chapterRepository.findByInhalt(testInhalt);
		assertNotNull(result);
    }

	@Test
	public void testDeleteChapter(){
		// given
		final String testInhalt = "Testinhalt";
		mangaRepository.save(testManga);
		Chapter testChapter = new Chapter(testInhalt, testManga);
		chapterRepository.save(testChapter);

		// when
		chapterRepository.delete(testChapter);

		// then
		Optional<Chapter> result = chapterRepository.findByInhalt(testInhalt);
		assertEquals(Optional.empty(), result);
	}
}