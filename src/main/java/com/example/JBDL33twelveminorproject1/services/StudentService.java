package com.example.JBDL33twelveminorproject1.services;

import com.example.JBDL33twelveminorproject1.models.MyUser;
import com.example.JBDL33twelveminorproject1.models.Student;
import com.example.JBDL33twelveminorproject1.repositories.StudentRepository;
import com.example.JBDL33twelveminorproject1.request.StudentCreateRequest;
import com.example.JBDL33twelveminorproject1.request.UserCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    MyUserDetailsService userDetailsService;

    public void create(StudentCreateRequest studentCreateRequest){

        //create user first
        UserCreateRequest userCreateRequest = studentCreateRequest.toUser();
        MyUser myUser = userDetailsService.createUser(userCreateRequest);


        //convert request to student class
        Student student = studentCreateRequest.to();
        student.setMyUser(myUser);
        studentRepository.save(student);
    }

    public Student findStudentByStudentID(int sId){
        return studentRepository.findById(sId).orElse(null);
    }
}
