package com.demo.hareoas.model;

import com.demo.hareoas.entity.Book;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

/**
 * @author wayne
 * @version BookModel,  2021/6/17
 */
@Data
public class BookListModel extends RepresentationModel<BookListModel> {

    private final List<Book> content;

    @JsonCreator
    public BookListModel(@JsonProperty("content") List<Book> content) {
        this.content = content;
    }

}
