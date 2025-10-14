package com.example.submission.service;

import com.example.submission.entity.Student;
import java.util.List;
import java.util.Optional;

public interface IStudentService {

    Student createStudent(Student student);
    List<Student> getAllStudents();
    Optional<Student> getStudentById(Long id);
    Student updateStudent(Long id, Student updatedStudent);
    void deleteStudent(Long id);
}

