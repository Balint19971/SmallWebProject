package edu.bbte.bibliospringdata;

import edu.bbte.bibliospringdata.model.Author;
import edu.bbte.bibliospringdata.model.Book;
import edu.bbte.bibliospringdata.service.BookService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BibliospringDataApplication {

    @Autowired
    private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(BibliospringDataApplication.class, args);
    }

    @PostConstruct
    public void run(){
//        Book book = new Book();
//        book.setTitle("Mulan");
//        book.setPageNumber(340);
//        book.setPublishingDate("10.03.1998");
//        Author author = new Author();
//        author.setName("Jozsef Attila");
//        Author author1 = new Author();
//        author1.setName("Moricz Zsigmond");
//        book.addAuthor(author);
//        book.addAuthor(author1);
//        bookService.create(book);
    }

}
