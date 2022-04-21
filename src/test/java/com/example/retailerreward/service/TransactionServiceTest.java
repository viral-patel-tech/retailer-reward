package com.example.retailerreward.service;

import com.example.retailerreward.model.Transaction;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.*;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TransactionServiceTest {
    @Autowired
    private TransactionService transactionService;

    @Test
    @Order(1)
    public void saveTransactions(){
        Transaction t1 = new Transaction();
        t1.setCustomerId("4");
        t1.setDate(new Date());
        t1.setTransactionAmount(17500);
        Transaction saved = transactionService.createTransaction(t1);

        assertNotNull(saved);
        assertTrue(saved instanceof Transaction);
    }

    @Test
    @Order(2)
    public void getAllUniqueCustomers(){
        List<String> allUniqueCustomers = transactionService.getAllUniqueCustomers();
        assertNotNull(allUniqueCustomers);
        hasSize(1);
    }
}
