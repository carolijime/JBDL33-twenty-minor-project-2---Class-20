package com.example.JBDL33twelveminorproject1.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

//has to be serializable to be saved in cache (redis)
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Admin implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int age;

    //automatically will add the creation date to the record
    @CreationTimestamp
    private Date createdOn;

    //automatically will add/update the update time to the record
    @UpdateTimestamp
    private Date updatedOn;

    @OneToOne
    @JoinColumn
    @JsonIgnoreProperties({"admin", "student", "password"})
    private MyUser myUser;
}
