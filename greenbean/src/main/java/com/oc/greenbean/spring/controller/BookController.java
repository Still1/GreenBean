package com.oc.greenbean.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {
    @GetMapping("/search")
    public String search(String searchKeyWord) {
        return "search";
    }
}
