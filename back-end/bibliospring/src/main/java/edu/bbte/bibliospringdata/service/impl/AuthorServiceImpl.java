package edu.bbte.bibliospringdata.service.impl;

import edu.bbte.bibliospringdata.model.Author;
import edu.bbte.bibliospringdata.repository.AuthorRepository;
import edu.bbte.bibliospringdata.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author create(Author author) {
        return authorRepository.saveAndFlush(author);
    }

    @Override
    public Author update(Author author) {
        return authorRepository.saveAndFlush(author);
    }

    @Override
    public boolean deleteById(Long id) {
        if(authorRepository.existsById(id)){
            authorRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author getById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }
}
