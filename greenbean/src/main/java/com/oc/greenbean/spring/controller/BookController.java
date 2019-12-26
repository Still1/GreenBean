package com.oc.greenbean.spring.controller;

import com.oc.greenbean.dto.SearchPageDto;
import com.oc.greenbean.spring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/search")
    public String search(String searchKeyWord, Integer start, Model model) {
        if(start == null) {
            start = 0;
        }
        SearchPageDto searchPageDto = bookService.getSearchPage(searchKeyWord, start);
        model.addAttribute("bookItems", searchPageDto.getBookItems());
        model.addAttribute("pagination", searchPageDto.getPagination());
        return "search";
    }

    @GetMapping("/addBook")
    public String addBook() {
        return "addBook";
    }
}
