package com.scanbuy.scanbook.repository;

import com.scanbuy.scanbook.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookInterface extends JpaRepository<Book, Integer> {
    public Book findByISBN(int ISBN);
}
