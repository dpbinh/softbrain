package com.dpbinh.softbrain.rest;

import com.dpbinh.softbrain.dto.*;
import com.dpbinh.softbrain.service.AuthorService;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authors")
//exception already handled by RestExceptionHandler
public class AuthorController {
    private static final Logger logger = LoggerFactory.getLogger(AuthorController.class);

    @Autowired
    private AuthorService authorService;

    @PostMapping()
    public ResponseEntity<AuthorDTO> add(@RequestBody AddAuthorRequest addAuthorRequest) {
        logger.info("Received request add new author: {}", addAuthorRequest);
        AuthorDTO authorDTO = authorService.add(addAuthorRequest);
        logger.info("New Author added: {}", authorDTO);
        return ResponseEntity.ok(authorDTO);
    }

    @GetMapping()
    public ResponseEntity<PageDTO<AuthorDTO>> getAll(PagingRequestDTO pagingRequestDTO) {
        logger.info("Received request get all authors");
        PageDTO<AuthorDTO> authorDTOS = authorService.getAll(pagingRequestDTO.getPage(), pagingRequestDTO.getLimit());
        return ResponseEntity.ok(authorDTOS);
    }

    @GetMapping("/{author-id}")
    public ResponseEntity<AuthorDTO> getById(@PathVariable(value = "author-id") Integer authorId) throws NotFoundException {
        logger.info("Received request get author by id {}", authorId);
        AuthorDTO authorDTO = authorService.get(authorId);
        return ResponseEntity.ok(authorDTO);
    }

    @PutMapping("/{author-id")
    public ResponseEntity<AuthorDTO> update(@PathVariable(value = "author-id") Integer authorId,
                                            @RequestBody UpdateAuthorRequest updateAuthorRequest) throws NotFoundException {
        logger.info("Received request update author id {} with {}", authorId, updateAuthorRequest);
        AuthorDTO authorDTO = authorService.update(authorId, updateAuthorRequest);
        return ResponseEntity.ok(authorDTO);
    }

    @DeleteMapping("/{author-id}")
    public ResponseEntity delete(@PathVariable(value = "author-id") Integer authorId) throws NotFoundException {
        logger.info("Received request delete author with id {}", authorId);
        authorService.delete(authorId);
        return ResponseEntity.ok().build();
    }
}
