package ch.wiss.pruefung_294_295.MangaController;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import ch.wiss.pruefung_294_295.model.ERole;
import ch.wiss.pruefung_294_295.model.Role;
import ch.wiss.pruefung_294_295.repository.RoleRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @class RoleTest
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
public class RoleTest {

    @MockBean
    private TestEntityManager entityManager;
    @MockBean
    private RoleRepository roleRepository;

    @Test
    public void testManager() {
        assertNotNull(entityManager);
    }

    @Test
    public void testRepository() {
        assertNotNull(roleRepository);
    }

    @Test
    public void testAddRole() {
        // given
        final ERole roleName = ERole.ROLE_ADMIN;
        Role testRole = new Role(roleName);

        //when
        roleRepository.save(testRole);

        //then
        Optional<Role> result = roleRepository.findByName(roleName);
        assertTrue(result.isPresent());
        assertEquals(result.get().getName(), roleName);
    }

    @Test
	public void testDeleteRole(){
		// given
        final ERole rolename = ERole.ROLE_ADMIN;
        Role testRole = new Role(rolename);
        roleRepository.save(testRole);
		
        // when
		roleRepository.delete(testRole);

		// then
		Optional<Role> result = roleRepository.findByName(rolename);
		assertEquals(Optional.empty(), result);
	}
    
}