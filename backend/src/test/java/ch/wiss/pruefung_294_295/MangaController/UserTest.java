package ch.wiss.pruefung_294_295.MangaController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import ch.wiss.pruefung_294_295.model.User;
import ch.wiss.pruefung_294_295.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class UserTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testManager() {
        assertNotNull(entityManager);
    }

    @Test
    public void testRepository() {
        assertNotNull(userRepository);
    }

    @Test
    public void testAddEntry() {
        // given
        final String username = "test";
        final String email = "test@test.com";
        final String password = "test123";

        //when
        User test = new User(username, email, password);

        userRepository.save(test);

        //then
        Optional<User> result = userRepository.findByUsername(username);
        assertNotNull(result);
        assertEquals(result.get().getUsername(), username);
    }
}