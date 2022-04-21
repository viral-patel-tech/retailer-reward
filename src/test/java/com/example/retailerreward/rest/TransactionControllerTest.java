package com.example.retailerreward.rest;


import com.example.retailerreward.service.TransactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc
public class TransactionControllerTest {

    @MockBean
    private TransactionService transactionService;

    @Autowired
    TransactionController transactionController;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void createValidTransaction() throws Exception{
        String transaction = "{\n" +
                "  \"customerId\": \"2\",\n" +
                "  \"date\": \"2022-04-21T00:30:17.485Z\",\n" +
                "  \"transactionAmount\": 350\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/createTransaction")
                .content(transaction)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void createInvalidTransaction() throws Exception{
        String transaction = "{\n" +
                "  \"customerId\": \"\",\n" +
                "  \"date\": \"2022-04-21T00:30:17.485Z\",\n" +
                "  \"transactionAmount\": 350\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/createTransaction")
                        .content(transaction)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
