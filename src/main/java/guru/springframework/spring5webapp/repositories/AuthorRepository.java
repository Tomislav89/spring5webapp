package guru.springframework.spring5webapp.repositories;

import guru.springframework.spring5webapp.domain.Author;
import org.springframework.data.repository.CrudRepository;

//CRUD repo koji prima Tip i Id kao parametre

public interface AuthorRepository extends CrudRepository <Author, Long>{
}
