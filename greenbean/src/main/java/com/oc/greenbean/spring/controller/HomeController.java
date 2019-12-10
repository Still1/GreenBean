package com.oc.greenbean.spring.controller;

import com.oc.greenbean.config.GreenbeanConfig;
import com.oc.greenbean.domain.User;
import com.oc.greenbean.spring.service.MyBookService;
import com.oc.greenbean.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;


@Controller
public class HomeController {

    private MyBookService myBookService;
    private UserService userService;

    @Autowired
    public HomeController(MyBookService myBookService, UserService userService) {
        this.myBookService = myBookService;
        this.userService = userService;
    }

    @RequestMapping(value = "/home")
    public String home(Model model, Principal principal, @Value("${bookPicturesPath}") String bookPicturesPath) {
        String username = principal.getName();
        User user = userService.getUserByUsername(username);
        Integer userId = user.getId();
        Integer readingCount = myBookService.getMyBookCount(GreenbeanConfig.READING_BOOK_TYPE, userId);
        Integer readCount = myBookService.getMyBookCount(GreenbeanConfig.READ_BOOK_TYPE, userId);
        model.addAttribute("readingCount", readingCount);
        model.addAttribute("readCount", readCount);

        List<String> readingPictures = myBookService.getMyBookPictures(GreenbeanConfig.READING_BOOK_TYPE, userId);
        List<String> readPictures = myBookService.getMyBookPictures(GreenbeanConfig.READ_BOOK_TYPE, userId);
        model.addAttribute("readingPictures", readingPictures);
        model.addAttribute("readPictures", readPictures);

        return "home";
    }
}
