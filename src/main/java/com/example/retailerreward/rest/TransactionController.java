package com.example.retailerreward.rest;

import com.example.retailerreward.model.Transaction;
import com.example.retailerreward.model.TransactionReport;
import com.example.retailerreward.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(@Autowired TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @GetMapping("/getUniqueCustomers")
    public ResponseEntity<List<String>> getUniqueCustomerIds() {
        List<String> customerList = transactionService.getAllUniqueCustomers();
        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }

    @GetMapping("/getRewardsByCustomerId/{id}")
    public ResponseEntity<TransactionReport> getRewardsByCustomerId(@PathVariable ("id") String id){
        TransactionReport transactionReport = transactionService.getRewardsForSpecificCustomer(id);

        return new ResponseEntity<>(transactionReport, HttpStatus.OK);
    }

    @PostMapping("/createTransaction")
    public ResponseEntity<Transaction> createTransaction(@Valid @RequestBody Transaction transaction){
        return new ResponseEntity<>(transactionService.createTransaction(transaction), HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> validationExceptionHandler(MethodArgumentNotValidException ex){
        Map<String, String> validationErrors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String propertyName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();

            validationErrors.put(propertyName, errorMessage);
        });
        return validationErrors;
    }
}
