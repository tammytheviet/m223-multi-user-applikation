package ch.wiss.pruefung_294_295.Tests;

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
    public void testRegisterUser() {
        // given
        final String testUsername = "test";
        final String testEmail = "test@test.com";
        final String testPassword = "test123";
        User testUser = new User(testUsername, testEmail, testPassword);

        //when
        userRepository.save(testUser);

        //then
        Optional<User> result = userRepository.findByUsername(testUsername);
        assertNotNull(result);
        assertEquals(result.get().getUsername(), testUsername);
    }

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