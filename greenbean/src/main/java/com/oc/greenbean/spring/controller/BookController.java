package com.oc.greenbean.spring.controller;

import com.oc.greenbean.dto.BookDto;
import com.oc.greenbean.dto.SearchPageDto;
import com.oc.greenbean.spring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
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
    //TODO 表单数据验证
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

    //TODO 出错视图
}
