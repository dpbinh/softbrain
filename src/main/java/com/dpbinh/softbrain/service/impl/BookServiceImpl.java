package com.dpbinh.softbrain.service.impl;

import com.dpbinh.softbrain.dto.*;
import com.dpbinh.softbrain.entity.Author;
import com.dpbinh.softbrain.entity.Book;
import com.dpbinh.softbrain.repository.BookRepository;
import com.dpbinh.softbrain.service.AuthorService;
import com.dpbinh.softbrain.service.BookService;
 import com.google.common.base.Preconditions;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorService authorService;

    @Override
    @Transactional
    public BookDTO add(AddBookRequest addBookRequest) throws NotFoundException {
        Preconditions.checkArgument(!StringUtils.isEmpty(addBookRequest.getTitle()), "Title is empty");
        Preconditions.checkArgument(addBookRequest.getTotalPage() != null, "Total page is empty");
        Preconditions.checkArgument(addBookRequest.getPublishedDate() != null, "Published date is empty");
        Preconditions.checkArgument(addBookRequest.getAuthorId() != null, "Author id is empty");

        BookDTO response;

        try {
            Author author = authorService.getAuthor(addBookRequest.getAuthorId());
            Book book = new Book();
            book.setTitle(addBookRequest.getTitle());
            book.setTotalPage(addBookRequest.getTotalPage());
            book.setAuthor(author);
            book.setPublishedDate(Instant.ofEpochMilli(addBookRequest.getPublishedDate()));
            Book savedBook = bookRepository.save(book);

            response = new BookDTO(savedBook);

        } catch (Exception e) {
            logger.error("Add new author failed", e);
            throw e;
        }

        return response;
    }

    @Override
    public PageDTO<BookDTO> getAll(Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page, limit);
        Page<Book> authorPaged = bookRepository.findAll(pageable);
        List<BookDTO> bookDTOS = authorPaged.stream().map(BookDTO::new).collect(Collectors.toList());
        return new PageDTO<>(authorPaged.getTotalPages(), bookDTOS);
    }

    @Override
    public BookDTO get(Long bookId) throws NotFoundException {
        Preconditions.checkArgument(bookId != null, "Book id is empty");
        Book book = getBook(bookId);
        return new BookDTO(book);
    }

    @Override
    @Transactional
    public BookDTO update(Long bookId, UpdateBookRequest updateBookRequest) throws NotFoundException {
        Preconditions.checkArgument(bookId != null, "Book id is empty");
        Book book = getBook(bookId);

        try {
            if(!StringUtils.isEmpty(book.getTitle())) {
                book.setTitle(updateBookRequest.getTitle());
            }

            if(book.getTotalPage() != null) {
                book.setTotalPage(updateBookRequest.getTotalPage());
            }

            if(book.getPublishedDate() != null) {
                book.setPublishedDate(Instant.ofEpochMilli(updateBookRequest.getPublishedDate()));
            }

            if(updateBookRequest.getAuthorId() != null && (book.getAuthor() == null || updateBookRequest.getAuthorId().equals(book.getAuthor().getId()))) {
                Author author = authorService.getAuthor(updateBookRequest.getAuthorId());
                book.setAuthor(author);
            }

            bookRepository.save(book);
        } catch (Exception e) {
            logger.error(String.format("Update book for id %s failed", bookId), e);
            throw e;
        }

        return new BookDTO(book);
    }

    @Override
    @Transactional
    public void delete(Long bookId) {
        Preconditions.checkArgument(bookId != null, "Book id is empty");

        try {
            bookRepository.deleteById(bookId);
        } catch (Exception e) {
            logger.error(String.format("Delete book with id %s is failed", bookId), e);
            throw e;
        }
    }

    @Override
    public PageDTO<BookDTO> search(BookSearchDTO bookSearchDTO) {
        Pageable pageable = PageRequest.of(bookSearchDTO.getPage(), bookSearchDTO.getLimit());
        Page<Book> bookPage = bookRepository.findAllByAuthor_NameContainsIgnoreCase(bookSearchDTO.getAuthorName(), pageable);

        List<BookDTO> bookDTOS = bookPage.getContent().stream().map(BookDTO::new).collect(Collectors.toList());
        return new PageDTO<>(bookPage.getTotalPages(), bookDTOS);
    }

    private Book getBook(Long bookId) throws NotFoundException {
        Optional<Book> bookOptional = bookRepository.findById(bookId);

        if(!bookOptional.isPresent()) {
            throw new NotFoundException(String.format("Not found book for id %s", bookId));
        }

        return bookOptional.get();
    }
}
