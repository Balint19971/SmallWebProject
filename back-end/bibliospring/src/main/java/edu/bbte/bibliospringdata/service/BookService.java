package edu.bbte.bibliospringdata.service;

import edu.bbte.bibliospringdata.model.Book;
import edu.bbte.bibliospringdata.model.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BookService {

    Book create(Book book);

    Book update(Book book);

    boolean deleteById(Long id);

    List<Book> getAll();

    Book getById(Long id);
}
