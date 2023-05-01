package com.example.webbshop_backend1.Controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerControllerTest {
    public static final String HOST = "http://localhost:";
    @LocalServerPort
    private static int PORT;
    public static final String BASE_PATH = "/customers";
    public static final String BASE_URL = HOST + PORT + BASE_PATH;

    @Autowired
    private MockMvc mockMvc;


    /* HAPPY PATH*/


    @Test
    public void should_throw_NotFoundException_if_no_customer_returned_from_database() throws Exception {

        this.mockMvc
                .perform(get("/customers"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"));
    }


}