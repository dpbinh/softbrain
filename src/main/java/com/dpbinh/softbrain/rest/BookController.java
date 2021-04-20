package com.dpbinh.softbrain.rest;

import com.dpbinh.softbrain.dto.*;
import com.dpbinh.softbrain.service.BookService;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/books")
//exception already handled by RestExceptionHandler
public class BookController {
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @PostMapping()
    public ResponseEntity<BookDTO> add(@RequestBody AddBookRequest addBookRequest) throws NotFoundException {
        logger.info("Received request add new book: {}", addBookRequest);
        BookDTO bookDTO = bookService.add(addBookRequest);
        logger.info("New Author added: {}", bookDTO);
        return ResponseEntity.ok(bookDTO);
    }

    @GetMapping()
    public ResponseEntity<PageDTO<BookDTO>> getAll(PagingRequestDTO pagingRequestDTO) {
        logger.info("Received request get all books");
        PageDTO<BookDTO> bookDTOs = bookService.getAll(pagingRequestDTO.getPage(), pagingRequestDTO.getLimit());
        return ResponseEntity.ok(bookDTOs);
    }

    @GetMapping("/{book-id}")
    public ResponseEntity<BookDTO> getById(@PathVariable(value = "book-id") Long bookId) throws NotFoundException {
        logger.info("Received request get book by id {}", bookId);
        BookDTO bookDTO = bookService.get(bookId);
        return ResponseEntity.ok(bookDTO);
    }

    @PutMapping("/{book-id")
    public ResponseEntity<BookDTO> update(@PathVariable(value = "book-id") Long bookId,
                                          @RequestBody UpdateBookRequest updateBookRequest) throws NotFoundException {
        logger.info("Received request update book id {} with {}", bookId, updateBookRequest);
        BookDTO bookDTO = bookService.update( bookId, updateBookRequest);
        return ResponseEntity.ok(bookDTO);
    }

    @DeleteMapping("/{book-id}")
    public ResponseEntity<BookDTO> delete(@PathVariable(value = "book-id") Long bookId) {
        logger.info("Received request delete book with id {}", bookId);
        bookService.delete(bookId);
        return ResponseEntity.ok().build();    }

    @GetMapping("/search")
    public ResponseEntity<PageDTO<BookDTO>> search(@Valid BookSearchDTO bookSearchDTO) {
        logger.info("Received request search book {}", bookSearchDTO);
        PageDTO<BookDTO> bookDTOPageDTO = bookService.search(bookSearchDTO);
        return ResponseEntity.ok(bookDTOPageDTO);
    }
}
