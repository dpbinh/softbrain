package com.dpbinh.softbrain.service;

import com.dpbinh.softbrain.dto.*;
import javassist.NotFoundException;

public interface BookService {
    BookDTO add(AddBookRequest addBookRequest) throws NotFoundException;

    PageDTO<BookDTO> getAll(Integer page, Integer limit);

    BookDTO get(Long bookId) throws NotFoundException;

    BookDTO update(Long bookId, UpdateBookRequest updateBookRequest) throws NotFoundException;

    void delete(Long bookId);

    PageDTO<BookDTO> search(BookSearchDTO bookSearchDTO);
}
