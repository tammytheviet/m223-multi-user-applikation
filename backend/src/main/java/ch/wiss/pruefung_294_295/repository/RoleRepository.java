package ch.wiss.pruefung_294_295.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.wiss.pruefung_294_295.model.ERole;
import ch.wiss.pruefung_294_295.model.Role;

@Repository
public interface RoleRepository extends JpaRepository < Role, Long > {
    Optional <Role> findByName(ERole name);
}