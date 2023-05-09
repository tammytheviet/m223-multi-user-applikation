package ch.wiss.pruefung_294_295.MangaController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ch.wiss.pruefung_294_295.controller.ChapterController;
import ch.wiss.pruefung_294_295.controller.MangaController;
import ch.wiss.pruefung_294_295.repository.ChapterRepository;
import ch.wiss.pruefung_294_295.repository.MangaRepository;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

@ExtendWith(SpringExtension.class)
@WebMvcTest
@AutoConfigureMockMvc
public class ChapterControllerTest {
	
	@MockBean private MangaRepository mangaRepository;
	@MockBean private ChapterRepository chapterRepository;
	
	@Mock MangaController mangaController;
	@Mock ChapterController chapterController;
	
	@Test
	public void whenChapterControllerInjected_thenNotNull() throws Exception {
		assertThat(chapterController).isNotNull(); //hier wird geprüft, ob unser SUT existiert
	}
}