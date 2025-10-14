package com.example.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.notification.entity.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}