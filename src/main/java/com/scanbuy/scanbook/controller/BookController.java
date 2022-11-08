package com.scanbuy.scanbook.controller;

import com.scanbuy.scanbook.model.Book;
import com.scanbuy.scanbook.repository.BookInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookInterface bookInterface;
    @GetMapping("/allBooks")
    public List<Book> getAllBooks() {
        return bookInterface.findAll();
    }

    @GetMapping("/findBook/{ISBN}")
    public Book getBook(@PathVariable("ISBN") String ISBN) {
        System.out.println("isbn "+ISBN);
        Book u = bookInterface.findByISBN(Integer.parseInt(ISBN));
        System.out.println("check find " + u);
        if (u != null) {
            System.out.println("book" + u);
            return u;
        }
        return u;
    }

    @PostMapping("/addbook")
    @ResponseBody
    public Book addBook(@RequestBody Book u) {
        System.out.println("user data " + u.getISBN());
        bookInterface.save(u);
        return u;
    }

    @PostMapping("/deletebook")
    @ResponseBody
        public boolean deleteBook(@RequestBody int isbn) {
        //System.out.println(ISBN);
        Book u = bookInterface.findByISBN(isbn);
        System.out.println("check find " + u);
        if (u != null) {
            System.out.println("book" + u);

            bookInterface.delete(u);
            return true;
        }
        return false;
    }

    @PostMapping("/updatebook")
    @ResponseBody
    public Book updateBook(@RequestBody Book u) {
        Book updateBook = bookInterface.findByISBN(u.getISBN());
        if(updateBook!=null){
            updateBook.setTitle(u.getTitle());
            updateBook.setAuthor(u.getAuthor());
            updateBook.setNumOfPages(u.getNumOfPages());
            bookInterface.save(updateBook);
            return updateBook;
        }
        return u;
    }
}
