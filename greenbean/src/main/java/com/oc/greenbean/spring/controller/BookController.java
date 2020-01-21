package com.oc.greenbean.spring.controller;

import com.oc.greenbean.dto.BookDto;
import com.oc.greenbean.dto.BookPageDto;
import com.oc.greenbean.dto.SearchPageDto;
import com.oc.greenbean.dto.UserRatingDto;
import com.oc.greenbean.spring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

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

    @GetMapping("/book/{id}/edit")
    public String editBookPage(@PathVariable Integer id, Model model) {
        BookDto bookDto = this.bookService.getBookDto(id);
        model.addAttribute("bookDto", bookDto);
        return "editBook";
    }

    @GetMapping("/book/{id}/updatePicture")
    public String editBookPicturePage(@PathVariable Integer id, Model model) {
        BookDto bookDto = this.bookService.getBookDto(id);
        model.addAttribute("bookDto", bookDto);
        return "editBookPicture";
    }

    @PostMapping("/book")
    //TODO 表单数据验证 字符串前后空格去掉
    public void addBook(BookDto bookDto, HttpServletResponse response) throws IOException {
        removeBlankName(bookDto.getAuthor());
        removeBlankName(bookDto.getTranslator());
        this.bookService.saveBook(bookDto);
        response.sendRedirect("addBookSuccess");
    }

    @PutMapping("/book")
    public void updateBook(BookDto bookDto, HttpServletResponse response) throws IOException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        removeBlankName(bookDto.getAuthor());
        removeBlankName(bookDto.getTranslator());
        this.setNullForBlankString(bookDto);
        this.bookService.updateBook(bookDto);
        response.sendRedirect("book/" + bookDto.getId());
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

    private void setNullForBlankString(Object object) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Class<?> objectClass = object.getClass();
        Method[] methods = objectClass.getDeclaredMethods();
        for(Method method : methods) {
            String methodName = method.getName();
            Class<?> returnType = method.getReturnType();
            if(methodName.startsWith("get") && returnType.equals(String.class)) {
                String returnValue = (String)method.invoke(object);
                if(returnValue != null && StringUtils.isEmptyOrWhitespace(returnValue)) {
                    String setter = "set" + methodName.substring(3);
                    Method setterMethod = objectClass.getMethod(setter, String.class);
                    setterMethod.invoke(object, new Object[] {null});
                }
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

    @PostMapping("/addOrUpdateUserRating")
    public void addOrUpdateUserRating(UserRatingDto dto, HttpSession session, HttpServletResponse response) throws IOException {
        //TODO 验证表单信息 验证userId非空
        dto.setUserId((Integer)session.getAttribute("userId"));
        this.bookService.addOrUpdateUserRating(dto);
        response.sendRedirect("book/" + dto.getBookId());
    }

    @PostMapping("/removeUserRating")
    public void removeUserRating(Integer bookId, HttpSession session, HttpServletResponse response) throws IOException {
        //TODO 验证表单信息
        Integer userId = (Integer)session.getAttribute("userId");
        this.bookService.removeUserRating(bookId, userId);
        response.sendRedirect("book/" + bookId);
    }

    @PostMapping("/updateBookPicture")
    public void updateBookPicture(@RequestParam(value = "id", required = true) Integer bookId, @RequestParam(value = "picture", required = true) MultipartFile picture, HttpServletResponse response) throws IOException {
        bookService.updatePicture(bookId, picture);
        response.sendRedirect("book/" + bookId);
    }

    //TODO 出错视图
}
