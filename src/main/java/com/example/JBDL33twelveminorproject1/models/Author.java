package com.example.JBDL33twelveminorproject1.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
//@Setter //no really needed
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    private int id;

    private String name;

    @Column(name = "land")
    private String country;
    private int age;

    //make unique identifiable field for when we save an author before saving a book
    @Column(unique = true, nullable = false)
    private String email;

    //by default the fetch type is lazy, we make this change so we could retrieve the authors with empty book lists
    //the fetch type eager will trigger the queries for "making" the booklist link
    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private List<Book> bookList;

    @CreationTimestamp
    private Date addedOn;
}
