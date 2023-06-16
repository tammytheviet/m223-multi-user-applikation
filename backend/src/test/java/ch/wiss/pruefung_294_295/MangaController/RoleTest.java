package ch.wiss.pruefung_294_295.MangaController;

import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import ch.wiss.pruefung_294_295.model.ERole;
import ch.wiss.pruefung_294_295.model.Role;
import ch.wiss.pruefung_294_295.repository.RoleRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class RoleTest {

    @Autowired private TestEntityManager entityManager;
    @Spy private RoleRepository roleRepositorySpy;

    @Test
    public void testManager() {
        assertNotNull(entityManager);
    }

    @Test
    public void testRepository() {
        assertNotNull(roleRepositorySpy);
    }

    @Test
    public void testAddRole() {
        // given
        final ERole rolename = ERole.ROLE_ADMIN;
        Role testRole = new Role(rolename);

        //when
        roleRepositorySpy.save(testRole);

        //then
        Optional<Role> result = roleRepositorySpy.findByName(rolename);
        assertEquals(result.get().getName(), rolename);
    }

    @Test
	public void testDeleteRole(){
		// given
        final ERole rolename = ERole.ROLE_ADMIN;
        Role testRole = new Role(rolename);
        roleRepositorySpy.save(testRole);
		
        // when
		roleRepositorySpy.delete(testRole);

		// then
		Optional<Role> result = roleRepositorySpy.findByName(rolename);
		assertEquals(Optional.empty(), result);
	}
    
}