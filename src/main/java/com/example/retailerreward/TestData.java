package com.example.retailerreward;

import com.example.retailerreward.model.Transaction;
import com.example.retailerreward.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TestData implements CommandLineRunner {
    private final TransactionRepository repo;

    public TestData(@Autowired TransactionRepository repo){
        this.repo = repo;
    }

    @Override
    public void run(String... args) throws Exception {

        Transaction t1 = new Transaction();
        t1.setDate(new Date());
        t1.setCustomerId("1");
        t1.setTransactionAmount(15000);

        Transaction t2 = new Transaction();
        t2.setDate(new Date());
        t2.setCustomerId("1");
        t2.setTransactionAmount(6500);

        Transaction t3 = new Transaction();
        t3.setDate(new Date());
        t3.setCustomerId("1");
        t3.setTransactionAmount(20100);

        Transaction t4 = new Transaction();
        t4.setDate(new Date());
        t4.setCustomerId("2");
        t4.setTransactionAmount(19000);

        Transaction t5 = new Transaction();
        t5.setDate(new Date());
        t5.setCustomerId("2");
        t5.setTransactionAmount(5000);

        Transaction t6 = new Transaction();
        Date date = new SimpleDateFormat("dd/MM/yyyy")
                .parse("25/12/2021");
        t6.setDate(date);
        t6.setCustomerId("2");
        t6.setTransactionAmount(20100);

        repo.save(t1);
        repo.save(t2);
        repo.save(t3);
        repo.save(t4);
        repo.save(t5);
        repo.save(t6);
    }
}
