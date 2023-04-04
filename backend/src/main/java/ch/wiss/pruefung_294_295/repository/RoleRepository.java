package ch.wiss.pruefung_294_295.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.wiss.pruefung_294_295.model.ERole;
import ch.wiss.pruefung_294_295.model.Role;

/**
 * Dieses Interface wird verwendet, um die Datenbankabfragen zu machen.
 * @class RoleRepository
 * @author Fabio Facundo & Tam Lai Nguyen
 * @version 1.0
 */
@Repository
public interface RoleRepository extends JpaRepository < Role, Long > {
    Optional <Role> findByName(ERole name);
}