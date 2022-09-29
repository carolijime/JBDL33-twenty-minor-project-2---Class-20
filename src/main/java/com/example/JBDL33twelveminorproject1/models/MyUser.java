package com.example.JBDL33twelveminorproject1.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collector;
import java.util.stream.Collectors;

// Only able to have ONE UserDetails so we create a user for handling students and admin users
//needs to be serializable to be saved in cache (redis)
@Entity
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyUser implements UserDetails, Serializable {

    private static final String AUTHORITY_DELIMITER = ":";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private int id;

    @Column(unique = true, nullable = false)
    private String username;
    private String password;

    @Getter
    private String authority;

    @OneToOne(mappedBy = "myUser")
    @Getter
    @JsonIgnoreProperties("myUser")
    private Student student;

    @OneToOne(mappedBy = "myUser")
    @Getter
    @JsonIgnoreProperties("myUser")
    private Admin admin;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String[] authorities = this.authority.split(AUTHORITY_DELIMITER);
        return Arrays.stream(authorities)
                .map(x -> new SimpleGrantedAuthority(x))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
