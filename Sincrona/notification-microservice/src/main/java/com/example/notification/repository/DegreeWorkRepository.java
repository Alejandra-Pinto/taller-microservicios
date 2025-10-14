package com.example.notification.repository;

import com.example.notification.entity.DegreeWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DegreeWorkRepository extends JpaRepository<DegreeWork, Long> {
}

