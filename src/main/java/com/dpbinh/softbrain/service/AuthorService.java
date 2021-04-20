package com.dpbinh.softbrain.service;

import com.dpbinh.softbrain.dto.AddAuthorRequest;
import com.dpbinh.softbrain.dto.AuthorDTO;
import com.dpbinh.softbrain.dto.PageDTO;
import com.dpbinh.softbrain.dto.UpdateAuthorRequest;
import com.dpbinh.softbrain.entity.Author;
import javassist.NotFoundException;

public interface AuthorService {
    AuthorDTO add(AddAuthorRequest addAuthorRequest);

    PageDTO<AuthorDTO> getAll(Integer page, Integer limit);

    AuthorDTO get(Integer authorId) throws NotFoundException;

    AuthorDTO update(Integer authorId, UpdateAuthorRequest updateAuthorRequest) throws NotFoundException;

    void delete(Integer authorId) throws NotFoundException;

    Author getAuthor(Integer authorId) throws NotFoundException;
}
