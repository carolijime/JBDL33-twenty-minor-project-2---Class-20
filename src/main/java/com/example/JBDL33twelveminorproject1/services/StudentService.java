package com.example.JBDL33twelveminorproject1.services;

import com.example.JBDL33twelveminorproject1.models.Student;
import com.example.JBDL33twelveminorproject1.repositories.StudentRepository;
import com.example.JBDL33twelveminorproject1.request.StudentCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public void create(StudentCreateRequest studentCreateRequest){
        //convert request to student class
        Student student = studentCreateRequest.to();
        studentRepository.save(student);
    }
}
