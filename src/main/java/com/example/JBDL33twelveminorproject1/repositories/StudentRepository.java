package com.example.JBDL33twelveminorproject1.repositories;

import com.example.JBDL33twelveminorproject1.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
