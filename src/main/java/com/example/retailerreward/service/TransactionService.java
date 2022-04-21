package com.example.retailerreward.service;

import com.example.retailerreward.model.Transaction;
import com.example.retailerreward.model.TransactionReport;
import com.example.retailerreward.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(@Autowired TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }

    public List<String> getAllUniqueCustomers() {
        return transactionRepository.uniqueCustomers();
    }

    public TransactionReport getRewardsForSpecificCustomer(String customerId){
        List<Transaction> transactions = transactionRepository.findByCustomerId(customerId);
        HashMap<String, Integer> mapofMonths = new HashMap<>();

        for(Transaction transaction: transactions){
            String dateKey = calculateMonth(transaction.getDate());
            if(mapofMonths.containsKey(dateKey)){
                Integer currentPoints = mapofMonths.get(dateKey);
                Integer newPoints = calculatePoints(transaction.getTransactionAmount());
                mapofMonths.put(dateKey, currentPoints + newPoints);
            } else {
                Integer newPoints = calculatePoints(transaction.getTransactionAmount());
                mapofMonths.put(dateKey, newPoints);
            }
        }

        TransactionReport transactionReport = new TransactionReport();
        transactionReport.setCustomerId(customerId);
        transactionReport.setMonthlyTotalMap(mapofMonths);

        transactionReport.setTotalRewards(getTotalRewards(mapofMonths));

        return transactionReport;
    }

    private String calculateMonth(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM - yyyy");
        return simpleDateFormat.format(date);
    }

    private Integer calculatePoints(Integer amount){
        Integer points = 0;
        if(amount < 5000){
            return 0;
        } else if(amount <= 10000){
            Integer difference = amount - 5000;
            points += difference / 100;
        } else {
            Integer difference = amount - 10000;
            points += (2 * (difference / 100)) + 50;
        }
        return points;
    }

    private Integer getTotalRewards(HashMap mapOfMonths){
        Iterator mapOfMonthsIterator = mapOfMonths.entrySet().iterator();

        Integer points = 0;

        while(mapOfMonthsIterator.hasNext()){
            Map.Entry entry = (Map.Entry) mapOfMonthsIterator.next();
            points += (Integer)entry.getValue();
        }
        return points;
    }

    public Transaction createTransaction(Transaction transaction) {

         return transactionRepository.save(transaction);
    }
}
