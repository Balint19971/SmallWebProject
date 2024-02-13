package edu.bbte.bibliospringdata.api;

import edu.bbte.bibliospringdata.api.exeption.BadRequestException;
import edu.bbte.bibliospringdata.api.exeption.CreationFaildException;
import edu.bbte.bibliospringdata.api.exeption.NotFoundException;
import edu.bbte.bibliospringdata.assembler.BookAssembler;
import edu.bbte.bibliospringdata.dto.incoming.BookInDTO;
import edu.bbte.bibliospringdata.dto.incoming.UserInDTO;
import edu.bbte.bibliospringdata.dto.outcoming.BookOutDTO;
import edu.bbte.bibliospringdata.dto.outcoming.UserOutDTO;
import edu.bbte.bibliospringdata.model.Book;
import edu.bbte.bibliospringdata.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookRestController {

    private final BookAssembler bookAssembler;
    private final BookService bookService;

    @Autowired
    public BookRestController(BookAssembler bookAssembler, BookService bookService){
        this.bookAssembler = bookAssembler;
        this.bookService = bookService;
    }

    @CrossOrigin
    @GetMapping("/books")
    public List<BookOutDTO> getAll(){
        List<BookOutDTO> bookOutDTOList = new ArrayList<>();

        for (Book book: bookService.getAll()) {
            bookOutDTOList.add(bookAssembler.bookToBookOutDTO(book));
        }

        return bookOutDTOList;
    }

    @CrossOrigin
    @GetMapping("books/{bookId}")
    public BookOutDTO getBookById(@PathVariable(value = "bookId") String stringId){
        long id;
        try {
            id = Long.parseLong(stringId) ;
        } catch (NumberFormatException e) {
            throw new BadRequestException(Book.class, stringId);
        }
        Book book = bookService.getById(id);
        if (book == null){
            throw new NotFoundException(Book.class, id);
        }

        return bookAssembler.bookToBookOutDTO(book);
    }

    @CrossOrigin
    @PostMapping("/books")
    public ResponseEntity<BookOutDTO> createBook(@RequestBody BookInDTO bookInDTO){
        BookOutDTO bookOutDTO = bookAssembler.bookToBookOutDTO(bookService.create(bookAssembler.bookInDTOToBook(bookInDTO)));
        URI uri = URI.create("/users/" + bookOutDTO.getId());
        return ResponseEntity.created(uri).body(bookOutDTO);
    }

    @CrossOrigin
    @PutMapping("/books")
    public ResponseEntity<BookOutDTO> updateBook(@RequestBody BookInDTO bookInDTO){
        Book dbBook = bookService.getById(bookInDTO.getId());
        Book updatedBook = bookAssembler.bookInDTOToBook(bookInDTO);
        updatedBook.setUuid(dbBook.getUuid());
        BookOutDTO bookOutDTO = bookAssembler.bookToBookOutDTO(bookService.update(updatedBook));
        URI uri = URI.create("/users/" + bookOutDTO.getId());
        return ResponseEntity.created(uri).body(bookOutDTO);
    }

    @CrossOrigin
    @DeleteMapping("/books/{id}")
    public boolean deleteBook(@PathVariable (value = "id") String stringId){
        long id;
        try {
            id = Long.parseLong(stringId) ;
        } catch (NumberFormatException e) {
            throw new BadRequestException(Book.class, stringId);
        }
        Book book = bookService.getById(id);
        if (book == null){
            throw new NotFoundException(Book.class, id);
        }
        return bookService.deleteById(id);
    }
}
