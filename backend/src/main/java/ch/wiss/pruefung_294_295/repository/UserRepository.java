package ch.wiss.pruefung_294_295.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.wiss.pruefung_294_295.model.User;

/**
 * Dieses Interface wird verwendet, um die Datenbankabfragen zu machen.
 * @class UserRepository
 * @author Fabio Facundo & Tam Lai Nguyen
 * @version 1.0
 */
@Repository
public interface UserRepository extends JpaRepository < User, Long > {
    Optional <User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}