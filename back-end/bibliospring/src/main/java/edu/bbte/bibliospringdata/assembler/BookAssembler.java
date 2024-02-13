package edu.bbte.bibliospringdata.assembler;

import edu.bbte.bibliospringdata.dto.incoming.BookInDTO;
import edu.bbte.bibliospringdata.dto.outcoming.BookOutDTO;
import edu.bbte.bibliospringdata.model.Author;
import edu.bbte.bibliospringdata.model.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookAssembler {

    public Book bookInDTOToBook(BookInDTO bookInDTO){
        Book book = new Book();
        if (bookInDTO.getId() != null){
            book.setId(bookInDTO.getId());
        }
        book.setTitle(bookInDTO.getTitle());
        book.setPageNumber(bookInDTO.getPageNumber());
        book.setPublishingDate(bookInDTO.getPublishingDate());
        //sql injection veszely!!!!!!!!!!!!!!!
        String[] authorsNameList = bookInDTO.getAuthorsNames().split(",");
        List<Author> authorsList = new ArrayList<>();
        for (String authorName: authorsNameList) {
            Author author = new Author();
            author.setName(authorName.trim());
            authorsList.add(author);
        }
        book.setAuthors(authorsList);
        return book;
    }

    public BookOutDTO bookToBookOutDTO(Book book){
        BookOutDTO bookOutDTO = new BookOutDTO();
        bookOutDTO.setUuid(book.getUuid());
        bookOutDTO.setId(book.getId());
        bookOutDTO.setTitle(book.getTitle());
        bookOutDTO.setPageNumber(book.getPageNumber());
        bookOutDTO.setPublishingDate(book.getPublishingDate());
        String authorsNames = "";
        for (int i = 0; i < book.getAuthors().size(); i++) {
            if(i + 1 < book.getAuthors().size()) {
                authorsNames += book.getAuthors().get(i).getName() + ", ";
            }else {
                authorsNames += book.getAuthors().get(i).getName();
            }
        }
        bookOutDTO.setAuthorsNames(authorsNames);
        return bookOutDTO;
    }
}
