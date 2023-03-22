package ch.wiss.pruefung_294_295.model;

import org.springframework.data.repository.CrudRepository;


//This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
//CRUD refers Create, Read, Update, Delete

public interface MangaRepository extends CrudRepository < Manga, Integer > {
	Manga deleteById(int id);
}