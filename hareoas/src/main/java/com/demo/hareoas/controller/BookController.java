package com.demo.hareoas.controller;

import com.demo.hareoas.entity.Book;
import com.demo.hareoas.model.BookModel;
import com.demo.hareoas.repository.BookRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * @author HP
 */
@RestController
public class BookController {

    @Resource
    private BookRepository bookRepository;

    @RequestMapping("/book/{id}")
    public HttpEntity<Book> getBook(@PathVariable("id") Long id) {
        Book book = bookRepository.findById(id).get();
        BookModel bookModel = new BookModel(book);
        bookModel.add(linkTo(methodOn(BookController.class).getBook(id)).withSelfRel());
        return new ResponseEntity(bookModel, HttpStatus.OK);
    }

    @RequestMapping("/books")
    public HttpEntity<Book> books() {
        Iterable<Book> books = bookRepository.findAll();
        List<BookModel> list = new ArrayList<>();
        books.forEach(h -> {
            BookModel bookModel = new BookModel(h);
            bookModel.add(linkTo(methodOn(BookController.class).getBook(h.getId())).withSelfRel());
            list.add(bookModel);
        });
        return new ResponseEntity(list, HttpStatus.OK);
    }
}
