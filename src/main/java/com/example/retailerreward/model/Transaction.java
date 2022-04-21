package com.example.retailerreward.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@NoArgsConstructor
@Entity
@ToString
public class Transaction {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Getter
    @Setter
    @NotBlank(message = "customerId cannot be blank")
    @NotEmpty(message = "customerId cannot be empty")
    @Min(value=0, message = "cannot be smaller than 0")
    private String customerId;
    @Getter
    @Setter
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Getter
    @Setter
    @Digits(integer = 10, fraction = 0)
    private Integer transactionAmount;
}
