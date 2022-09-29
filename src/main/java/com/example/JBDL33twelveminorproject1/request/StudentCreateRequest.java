package com.example.JBDL33twelveminorproject1.request;

import com.example.JBDL33twelveminorproject1.models.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentCreateRequest {

    @NotBlank //does not accept blanks nor nulls
    private String name;

    @NotBlank
    private String contact;

    private String address;

    private String email;

    @NotBlank
    private  String username;

    @NotBlank
    private String password;

    //this is ONLY to create a new student, that is why by default we set accountStatus as active
    public Student to(){
        return Student.builder()
                .name(name)
                .contact(contact)
                .address(address)
                .email(email)
                .accountStatus(AccountStatus.ACTIVE)
                .build();
    }

    public UserCreateRequest toUser(){
        return UserCreateRequest.builder()
                .student(to())
                .username(username)
                .password(password)
                .build();
    }

}
