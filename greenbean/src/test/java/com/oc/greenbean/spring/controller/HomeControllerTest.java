package com.oc.greenbean.spring.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

class HomeControllerTest {

    MockMvc mockMvc;

    @BeforeEach
    void setup() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("classpath:/template/");
        viewResolver.setSuffix(".html");
        this.mockMvc = MockMvcBuilders.standaloneSetup(new HomeController()).setViewResolvers(viewResolver).build();
    }

    @Test
    void testHome() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/home"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("home"));
    }
}
