package com.dpbinh.softbrain.repository;

import com.dpbinh.softbrain.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    Page<Book> findAllByAuthor_NameContainsIgnoreCase(String authorName, Pageable pageable);

}
