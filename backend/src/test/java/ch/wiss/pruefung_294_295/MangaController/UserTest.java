package ch.wiss.pruefung_294_295.MangaController;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import ch.wiss.pruefung_294_295.model.User;
import ch.wiss.pruefung_294_295.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @class UserTest
 * @author Fabio Facundo & Tam lai Nguyen
 * @version v1.0
 * 
 * Hier werden die verschiedenen funktionen des Repositorys getestet
 * 
 * @SpringBootTest erstellt den ApplicationContext welcher für die Tests verwendet wird.
 * @ActiveProfiles bestimmt, welches Bean Profil für das laden der ApplicationContext verwendet wird.
 * @MockBean wird verwendet, um ein Mock zu erstellen. Dieser Mock wird für die Tests verwendet, um nicht die echte Arbeitsumgebung mit den Tests zu beeinflussen.
 * @Mock wird verwendet, um ein Mock zu erstellen. Dieser Mock wird für die Tests verwendet, um nicht die echte Arbeitsumgebung mit den Tests zu beeinflussen.
 * @Test deklariert, dass dies ein Test ist, welcher auch ausgeführt werden kann.
 */

 @SpringBootTest
 @ActiveProfiles("test")
public class UserTest {

    @MockBean
    private TestEntityManager entityManager;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testManager() {
        assertNotNull(entityManager);
    }

    @Test
    public void testRepository() {
        assertNotNull(userRepository);
    }

    /*
    @Test
    public void testRegisterUser() {
        // given
        User testUser = new User("testUsername", "testEmail", "testPassword");

        //when
        userRepository.save(testUser);

        //then
        Optional<User> result = userRepository.findByUsername("testUsername");
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getUsername()).isEqualTo(testUser.getUsername());
        assertThat(result.get().getEmail()).isEqualTo(testUser.getEmail());
    }
    */

    @Test
	public void testDeleteUser(){
		// given
        final String testUsername = "test";
        final String testEmail = "test@test.com";
        final String testPassword = "test123";
        User testUser = new User(testUsername, testEmail, testPassword);
        userRepository.save(testUser);
		
        // when
		userRepository.delete(testUser);

		// then
		Optional<User> result = userRepository.findByUsername(testUsername);
		assertEquals(Optional.empty(), result);
	}
}