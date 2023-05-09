package ch.wiss.pruefung_294_295.MangaController;

import org.junit.jupiter.api.Test;
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

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
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
        final ERole rolename = ERole.ROLE_ADMIN;
        Role testRole = new Role(rolename);

        //when
        roleRepository.save(testRole);

        //then
        Optional<Role> result = roleRepository.findByName(rolename);
        assertEquals(result.get().getName(), rolename);
    }

    @Test
	public void testDeleteUser(){
		// given
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