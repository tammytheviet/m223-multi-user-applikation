package ch.wiss.pruefung_294_295.MangaController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ch.wiss.pruefung_294_295.controller.ChapterController;
import ch.wiss.pruefung_294_295.controller.MangaController;
import ch.wiss.pruefung_294_295.model.ChapterRepository;
import ch.wiss.pruefung_294_295.model.MangaRepository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class MangaControllerTest {
	
	@MockBean private MangaRepository mangaRepository;
	@MockBean private ChapterRepository chapterRepository;
	
	@Autowired MangaController mangaController;
	@Autowired ChapterController chapterController;
	
	String exampleManga = "{\"titel\":\"Testtitel\",\"genre\":\"Testgenre\",\"zeichner\":\"Testzeichner\",\"autor\":\"Testautor\",\"status\":\"Teststatus\",\"veröffentlichungsdatum\":\"2018-03-29\",\"gelesen\":\"true\",\"lesezeichen\":\"false\"}";

	@Test
	public void whenMangaControllerInjected_thenNotNull() throws Exception {
		assertThat(mangaController).isNotNull(); //hier wird geprüft, ob unser SUT existiert
	}
	
	/*@Test
    public void testFindAll() 
    {
        // given
		Manga manga1 = new Manga("Testtitel","Testgenre","Testzeichner","Testautor","Teststatus",2018-03-29,true,false);
		mangaRepository.save(manga1);
 
        // when
        Manga result = mangaController.getAllMangas();
    }*/
	
}