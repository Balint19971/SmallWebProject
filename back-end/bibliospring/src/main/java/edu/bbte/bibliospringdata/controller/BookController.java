package edu.bbte.bibliospringdata.controller;

import edu.bbte.bibliospringdata.assembler.BookAssembler;
import edu.bbte.bibliospringdata.dto.incoming.BookInDTO;
import edu.bbte.bibliospringdata.dto.outcoming.BookOutDTO;
import edu.bbte.bibliospringdata.model.Author;
import edu.bbte.bibliospringdata.model.Book;
import edu.bbte.bibliospringdata.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookAssembler bookAssembler;

    @GetMapping
    public String getBooks(Model model){
        List<Book> bookList = bookService.getAll();
        List<BookOutDTO> bookOutDTOList = new ArrayList<>();

        for (Book book: bookList) {
            bookOutDTOList.add(bookAssembler.bookToBookOutDTO(book));
        }

        model.addAttribute("books", bookOutDTOList);
        return "books/books-page";
    }

    @GetMapping("/addBookForm")
    public String addBookFrom(Model model){
        BookInDTO bookInDTO = new BookInDTO();

        model.addAttribute("book", bookInDTO);

        return "books/book-form";
    }

    @PostMapping("/createBook")
    public String createBook(@ModelAttribute("book") BookInDTO bookInDTO){
        Book book = bookAssembler.bookInDTOToBook(bookInDTO);

        bookService.create(book);

        return "redirect:/books";
    }

    @GetMapping("/updateBookForm")
    public String updateBookForm(@RequestParam("bookId") Long id,Model model){
        Book book = bookService.getById(id);

        BookOutDTO bookOutDTO = bookAssembler.bookToBookOutDTO(book);

        model.addAttribute("bookOutDTO", bookOutDTO);

        return "books/update-form";
    }

    @PostMapping("/updateBook")
    public String updateBook(@ModelAttribute("bookOutDTO") BookOutDTO bookOutDTO){
        Book book = new Book();
        book.setId(bookOutDTO.getId());
        book.setUuid(bookOutDTO.getUuid());
        book.setTitle(bookOutDTO.getTitle());
        book.setPageNumber(bookOutDTO.getPageNumber());
        book.setPublishingDate(bookOutDTO.getPublishingDate());
        String[] authorsNames = bookOutDTO.getAuthorsNames().split(",");
        List<Author> authors = new ArrayList<>();
        for (String authorName: authorsNames) {
            Author author = new Author(authorName);
            authors.add(author);
        }
        book.setAuthors(authors);
        bookService.update(book);
        return "redirect:/books";
    }

    @GetMapping("/deleteBook")
    public String deleteBook(@RequestParam("bookId") Long id){

        bookService.deleteById(id);

        return "redirect:/books";

    }
}
