package edu.bbte.bibliospringdata.service.impl;

import edu.bbte.bibliospringdata.model.Book;
import edu.bbte.bibliospringdata.repository.BookRepository;
import edu.bbte.bibliospringdata.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book create(Book book) {
        return bookRepository.saveAndFlush(book);
    }

    @Override
    public Book update(Book book) {
        return bookRepository.saveAndFlush(book);
    }

    @Override
    public boolean deleteById(Long id) {
        if(bookRepository.existsById(id)){
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book getById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }
}
