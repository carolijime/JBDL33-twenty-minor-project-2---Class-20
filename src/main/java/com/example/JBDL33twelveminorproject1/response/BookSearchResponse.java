package com.example.JBDL33twelveminorproject1.response;

import com.example.JBDL33twelveminorproject1.models.Author;
import com.example.JBDL33twelveminorproject1.models.Genre;
import com.example.JBDL33twelveminorproject1.models.Student;
import com.example.JBDL33twelveminorproject1.models.Transaction;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookSearchResponse {

    private int id;
    private String name;
    private int cost;

    private Genre genre;

    @JsonIgnoreProperties({"bookList", "addedOn"}) //ignores bookList and properties inside author class (no cycling references)
    private Author author;

    private Student student;

    private List<Transaction> transactionList;

    private Date createdOn;
    private Date updatedOn;

}
