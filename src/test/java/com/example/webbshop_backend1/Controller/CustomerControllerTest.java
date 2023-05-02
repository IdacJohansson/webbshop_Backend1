package com.example.webbshop_backend1.Controller;

import com.example.webbshop_backend1.Model.Customer;
import com.example.webbshop_backend1.Repo.CustomerRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerRepo mockRepo;

    private static final String RESPONSE_ALL_AS_JSON = """
            [{"id":1,"customerName":"Sara","ssn":"9701252123"},{"id":2,"customerName":"Lena","ssn":"7512071254"},{"id":3,"customerName":"William","ssn":"8010194546"},{"id":4,"customerName":"Emilia","ssn":"9012158789"}]""";

    private static final String POST_BODY = """
            {"customerName":"newCustomer","ssn":"newSSN"}
            """;

    @BeforeEach
    public void init() {
        Customer customer1 = new Customer(1L, "Sara", "9701252123");
        Customer customer2 = new Customer(2L, "Lena", "7512071254");
        Customer customer3 = new Customer(3L, "William", "8010194546");
        Customer customer4 = new Customer(4L, "Emilia", "9012158789");

        when(mockRepo.findById(1L)).thenReturn(Optional.of(customer1));
        when(mockRepo.findById(2L)).thenReturn(Optional.of(customer2));
        when(mockRepo.findById(3L)).thenReturn(Optional.of(customer3));
        when(mockRepo.findAll()).thenReturn(Arrays.asList(customer1, customer2, customer3, customer4));

    }

    @Test
    void should_return_all_customers() throws Exception {
        this.mockMvc.perform(get("/customer/all"))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.[0].id").value(1),
                        jsonPath("$.[0].customerName").value("Sara"),
                        jsonPath("$.[0].ssn").value("9701252123"),
                        content().string(RESPONSE_ALL_AS_JSON)
                );
    }

    @Test
    void should_return_true_if_customer_saved() throws Exception {
        Customer customer = new Customer("newCustomer", "newSSN");
        doReturn(customer).when(mockRepo).save(eq(customer));

        this.mockMvc.perform(post("/customer/add").content(POST_BODY)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void should_return_customer_by_id() throws Exception {
        this.mockMvc.perform(get("/customer/1"))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        jsonPath("$.id").value(1),
                        jsonPath("$.customerName").value("Sara"),
                        jsonPath("$.ssn").value("9701252123")
                );
    }

    /* Checking critical area */
    @Test
    void throw_NotFoundCustomerException_if_findCustomerById_not_find() throws Exception {
        this.mockMvc.perform(get("/customer/-1"))
                .andExpectAll(
                        status().is4xxClientError(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().string("There is no customer with id = -1")
                );
    }

    @Test
    void throw_NotSavedCustomerException_if_add_Not_valid_customer() throws Exception {
        Customer customer = new Customer("newCustomer", "newSSN");
        doReturn(customer).when(mockRepo).save(eq(customer));

        this.mockMvc.perform(post("/customer/add").content("""
                                {}
                                """)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        status().is5xxServerError(),
                        content().string(containsString("Something bad happen during saving customer="))
                );
    }
}