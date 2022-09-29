package com.example.JBDL33twelveminorproject1.controllers;

import com.example.JBDL33twelveminorproject1.models.MyUser;
import com.example.JBDL33twelveminorproject1.models.Student;
import com.example.JBDL33twelveminorproject1.request.BookCreateRequest;
import com.example.JBDL33twelveminorproject1.request.StudentCreateRequest;
import com.example.JBDL33twelveminorproject1.services.MyUserDetailsService;
import com.example.JBDL33twelveminorproject1.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/student")
    public void createStudent(@Valid @RequestBody StudentCreateRequest studentCreateRequest){
        studentService.create(studentCreateRequest);
    }

    // Only fro students
    @GetMapping("/student")
    public Student getStudent() throws Exception {
        //get student's details from the context (so student can only see hisher information and no other students' data
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUser myUser = (MyUser) authentication.getPrincipal();
        //double confirming that student is not null (if null, it is an admin user and not a student
        if(myUser.getStudent() == null) {
            throw new Exception("User requesting the details is not a student");
        }
        int studentId = myUser.getStudent().getId();
        //get student information using the retrieved id from context
        return studentService.findStudentByStudentID(studentId);
    }

    // Only for admins
    @GetMapping("/student_for_admin")
    public Student getStudentForAdmin(@RequestParam("studentId") int studentId) throws Exception {
        // check the person accessing this API is an admin or not

        //below double confirm that user making the request is an admin user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUser myUser = (MyUser) authentication.getPrincipal();
        //no admin information in user means user is a student
        if(myUser.getAdmin() == null){
            throw new Exception("User requesting the details is not an admin");
        }

        return studentService.findStudentByStudentID(studentId);
    }
}
