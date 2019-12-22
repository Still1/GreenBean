package com.oc.greenbean.spring.controller;

import com.oc.greenbean.dto.SearchBookItemDto;
import com.oc.greenbean.spring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/search")
    public String search(String searchKeyWord, Model model) {
        List<SearchBookItemDto> searchBookItemDtos = bookService.getSearchBooks(searchKeyWord);
        model.addAttribute("bookItems", searchBookItemDtos);
        return "search";
    }
}
