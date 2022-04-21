package com.example.retailerreward.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@NoArgsConstructor
public class TransactionReport {

    @Getter
    @Setter
    private String customerId;

    @Getter
    @Setter
    private HashMap<String, Integer> monthlyTotalMap;

    @Getter
    @Setter
    private Integer totalRewards;
}
