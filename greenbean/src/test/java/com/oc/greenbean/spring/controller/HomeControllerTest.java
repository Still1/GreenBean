package com.oc.greenbean.spring.controller;

import com.oc.greenbean.spring.service.MyBookService;
import com.oc.greenbean.spring.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

class HomeControllerTest {

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("classpath:/template/");
        viewResolver.setSuffix(".html");
        MyBookService myBookService = Mockito.mock(MyBookService.class);
        UserService userService = Mockito.mock(UserService.class);
        HomeController homeController = new HomeController(myBookService, userService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(homeController).setViewResolvers(viewResolver).build();
    }
}
