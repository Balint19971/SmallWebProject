package edu.bbte.bibliospringdata.repository;

import edu.bbte.bibliospringdata.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
