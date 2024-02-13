package edu.bbte.bibliospringdata.repository;

import edu.bbte.bibliospringdata.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
