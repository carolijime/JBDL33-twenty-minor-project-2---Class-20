package com.example.JBDL33twelveminorproject1.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(unique = true, nullable = false)
    private String contact; // +91
    private String address;

    @Column(unique = true)
    private String email;

    @Enumerated(value = EnumType.STRING)
    private AccountStatus accountStatus;

    //automatically will add the creation date to the record
    @CreationTimestamp
    private Date createdOn;

    //automatically will add/update the update time to the record
    @UpdateTimestamp
    private Date updatedOn;

    //create relationship between student and book, one student can have many books (1:N)
    //mappedby makes a by-directional relationship and "makes" a query like: select * from book where student_id = ?
    //mappedby "student" is the attribute's name in the book's class
    @OneToMany(mappedBy = "student")
    private List<Book> bookList;

    //one student can have multiple transaction (1:N)
    @OneToMany(mappedBy = "my_student")
    private List<Transaction> transactionList;

//    //one student can only have one account (1:1)
//    private StudentAccount studentAccount;
}
