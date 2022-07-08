package com.example.JBDL33twelveminorproject1.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
//Example on how to make a "composed" primary key (2 fields compose the primary key)
//needs to add a serialization in order to have 2 fields as primary key
public class TempModel implements Serializable {

    @Id
    private int id;

    @Id
    private String name;

    @CreationTimestamp
    private Date createOn;

//    @OneToMany(mappedBy = "tempModel")
//    private List<Book> bookList;

}
