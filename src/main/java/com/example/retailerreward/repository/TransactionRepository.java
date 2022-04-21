package com.example.retailerreward.repository;

import com.example.retailerreward.model.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    List<Transaction> findByCustomerId(String customerId);

    @Query("select distinct customerId from Transaction")
    List<String> uniqueCustomers();
}
