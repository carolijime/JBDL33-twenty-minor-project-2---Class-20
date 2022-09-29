package com.example.JBDL33twelveminorproject1.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

//has to be serializable to be saved in cache (redis)
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student implements Serializable {

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
    @OneToMany(mappedBy = "student")
    private List<Transaction> transactionList;

    @OneToOne
    @JoinColumn
    @JsonIgnoreProperties({"student", "admin", "password"}) //if not, circular reference
    private MyUser myUser;

//    //one student can only have one account (1:1)
//    private StudentAccount studentAccount;
}
