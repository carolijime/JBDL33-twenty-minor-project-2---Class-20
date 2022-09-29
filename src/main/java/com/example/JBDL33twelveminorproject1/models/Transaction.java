package com.example.JBDL33twelveminorproject1.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //unique, ID only for end user, like "tracking number" or "order number"
    //we will return this instead of the ID (for security)
    private String externalTxnId;

    @Enumerated
    private TransactionType transactionType;

    private double payment;

    @ManyToOne
    @JoinColumn
    private Book book;

    @ManyToOne
    @JoinColumn
    private Student student;

    @CreationTimestamp
    private Date transactionDate;
}
