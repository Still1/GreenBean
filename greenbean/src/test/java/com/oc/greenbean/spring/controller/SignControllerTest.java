package com.oc.greenbean.spring.controller;

import com.oc.greenbean.exception.UsernameDuplicatedException;
import com.oc.greenbean.spring.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

class SignControllerTest {

    private static String existUsername = "exist";
    private static String notExistUsername = "notExist";
    private static String defaultPassword = "password";

    private MockMvc mockMvc;
    private UserService mockUserService;

    @BeforeEach
    void setup() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("classpath:/template/");
        viewResolver.setSuffix(".html");
        mockUserService = Mockito.mock(UserService.class);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new SignController(mockUserService)).setViewResolvers(viewResolver).build();
    }

    @Test
    void testSignIn() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/signIn"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("signIn"));
    }

    @Test
    void testSignInError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/signInError"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("signIn"));
    }

    @Test
    void testSignUp() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/signUp")
            .param("username", notExistUsername)
            .param("password", defaultPassword)
            .param("confirmPassword", defaultPassword))
            .andExpect(MockMvcResultMatchers.view().name("signUpSuccess"));
        Mockito.verify(mockUserService).insertUser(Mockito.argThat((user) -> user.getUsername().equals(notExistUsername)));
    }

    @Test
    void testSignUpWithDuplicatedUsername() throws Exception {
        Mockito.doThrow(UsernameDuplicatedException.class).when(mockUserService)
            .insertUser(Mockito.argThat((user) -> user.getUsername().equals(existUsername)));
        mockMvc.perform(MockMvcRequestBuilders.post("/signUp")
            .param("username", existUsername)
            .param("password", defaultPassword)
            .param("confirmPassword", defaultPassword))
            .andExpect(MockMvcResultMatchers.status().isForbidden())
            .andExpect(MockMvcResultMatchers.view().name("signUpFail"));
    }
}
