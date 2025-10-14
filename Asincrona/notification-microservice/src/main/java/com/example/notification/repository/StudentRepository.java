package com.example.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.notification.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}