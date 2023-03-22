package ch.wiss.pruefung_294_295.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository < Role, Long > {
    Optional < Role > findByName(ERole name);
}