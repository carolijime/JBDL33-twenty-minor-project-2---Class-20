package com.example.JBDL33twelveminorproject1.repositories;

import com.example.JBDL33twelveminorproject1.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository //not necessary because SimpleJPARepository class has a repository annotation
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
