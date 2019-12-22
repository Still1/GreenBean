package com.oc.greenbean.spring.controller;

import com.oc.greenbean.spring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/search")
    public String search(String searchKeyWord) {
        bookService.getSearchBooks(searchKeyWord);
        return "search";
    }
}
