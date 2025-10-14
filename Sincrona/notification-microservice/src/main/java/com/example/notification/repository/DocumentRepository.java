package com.example.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.notification.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}