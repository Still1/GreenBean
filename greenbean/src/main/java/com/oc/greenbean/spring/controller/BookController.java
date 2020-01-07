package com.oc.greenbean.spring.controller;

import com.oc.greenbean.dto.BookDto;
import com.oc.greenbean.dto.BookPageDto;
import com.oc.greenbean.dto.SearchPageDto;
import com.oc.greenbean.dto.UserRatingDto;
import com.oc.greenbean.spring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

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
    public String addBookPage() {
        return "addBook";
    }

    @PostMapping("/book")
    //TODO 表单数据验证 字符串前后空格去掉
    public void addBook(BookDto bookDto, HttpServletResponse response) throws IOException {
        removeBlankName(bookDto.getAuthor());
        removeBlankName(bookDto.getTranslator());
        this.bookService.saveBook(bookDto);
        response.sendRedirect("addBookSuccess");
    }

    @GetMapping("/addBookSuccess")
    public String addBookSuccess() {
        return "addBookSuccess";
    }

    private void removeBlankName(List<String> nameList) {
        Iterator<String> iterator = nameList.iterator();
        //XXX Collection.removeIf
        while(iterator.hasNext()) {
            if(StringUtils.isEmptyOrWhitespace(iterator.next())) {
                iterator.remove();
            }
        }
    }


    @GetMapping("/getAuthorSuggestion")
    @ResponseBody
    public List<String> getAuthorSuggestion(String keyword) {
        return this.bookService.getAuthorSuggestion(keyword);
    }

    @GetMapping("/getTranslatorSuggestion")
    @ResponseBody
    public List<String> getTranslatorSuggestion(String keyword) {
        return this.bookService.getTranslatorSuggestion(keyword);
    }

    @GetMapping("/book/{id}")
    public String showBook(@PathVariable Integer id, HttpSession session, Model model) {
        Integer userId = (Integer)session.getAttribute("userId");
        BookPageDto bookPageDto = this.bookService.getBookPage(id, userId);
        model.addAttribute("bookPage", bookPageDto);
        return "book";
    }

    @PostMapping("/addUserRating")
    public void addUserRating(UserRatingDto dto, HttpSession session, HttpServletResponse response) throws IOException {
        //TODO 验证表单信息 验证userId非空
        dto.setUserId((Integer)session.getAttribute("userId"));
        this.bookService.addUserRating(dto);
        response.sendRedirect("book/" + dto.getBookId());
    }
    //TODO 出错视图
}
