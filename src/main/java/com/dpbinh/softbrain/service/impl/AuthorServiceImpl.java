package com.dpbinh.softbrain.service.impl;

import com.dpbinh.softbrain.dto.AddAuthorRequest;
import com.dpbinh.softbrain.dto.AuthorDTO;
import com.dpbinh.softbrain.dto.PageDTO;
import com.dpbinh.softbrain.dto.UpdateAuthorRequest;
import com.dpbinh.softbrain.entity.Author;
import com.dpbinh.softbrain.repository.AuthorRepository;
import com.dpbinh.softbrain.service.AuthorService;
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
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    private static final Logger logger = LoggerFactory.getLogger(AuthorServiceImpl.class);

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    @Transactional
    public AuthorDTO add(AddAuthorRequest addAuthorRequest) {
        Preconditions.checkArgument(!StringUtils.isEmpty(addAuthorRequest.getName()), "Author name is empty");

        AuthorDTO response;

        try {
            Author author = new Author();
            author.setName(addAuthorRequest.getName());
            Author savedAuthor = authorRepository.saveAndFlush(author);

            response = new AuthorDTO();
            response.setId(savedAuthor.getId());
            response.setName(savedAuthor.getName());

        } catch (Exception e) {
            logger.error("Add new author failed", e);
            throw e;
        }

        return response;
    }

    @Override
    public PageDTO<AuthorDTO> getAll(Integer page, Integer limit) {

        Pageable pageable = PageRequest.of(page, limit);
        Page<Author> authorPaged = authorRepository.findAll(pageable);

        List<AuthorDTO> authorDTOS = authorPaged.stream().map(AuthorDTO::new).collect(Collectors.toList());
        return new PageDTO<>(authorPaged.getTotalPages(), authorDTOS);
    }

    @Override
    public AuthorDTO get(Integer authorId) throws NotFoundException {
        Preconditions.checkArgument(authorId != null, "Author id is empty");
        Author author = getAuthor(authorId);
        return new AuthorDTO(author);
    }

    @Override
    @Transactional
    public AuthorDTO update(Integer authorId, UpdateAuthorRequest updateAuthorRequest) throws NotFoundException {
        Preconditions.checkArgument(authorId != null, "Author id is empty");
        Preconditions.checkArgument(!StringUtils.isEmpty(updateAuthorRequest.getName()), "Author name is empty");

        Author author = getAuthor(authorId);

        try {
            author.setName(updateAuthorRequest.getName());

            authorRepository.saveAndFlush(author);
        } catch (Exception e) {
            logger.error(String.format("Update author for id %s failed", authorId), e);
            throw e;
        }

        return new AuthorDTO(authorId, updateAuthorRequest.getName());
    }

    @Override
    @Transactional
    public void delete(Integer authorId) throws NotFoundException {
        Preconditions.checkArgument(authorId != null, "Author id is empty");
        Author author = getAuthor(authorId);
        try {
            if(CollectionUtils.isEmpty(author.getBooks())) {
                authorRepository.delete(author);
            } else {
                author.setDeleted(true);
                authorRepository.saveAndFlush(author);
            }
        } catch (Exception e) {
            logger.error(String.format("Delete author with id %s is failed", authorId), e);
            throw e;
        }
    }

    public Author getAuthor(Integer authorId) throws NotFoundException {
        Optional<Author> authorOptional = authorRepository.findById(authorId);
        if(!authorOptional.isPresent()) {
            throw new NotFoundException(String.format("Not found author for id %s", authorId));
        }

        return authorOptional.get();
    }

}
