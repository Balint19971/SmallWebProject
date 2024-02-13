package edu.bbte.bibliospringdata.service;

import edu.bbte.bibliospringdata.model.Author;
import edu.bbte.bibliospringdata.model.Book;

import java.util.List;

public interface AuthorService {

    Author create(Author author);

    Author update(Author author);

    boolean deleteById(Long id);

    List<Author> getAll();

    Author getById(Long id);
}
