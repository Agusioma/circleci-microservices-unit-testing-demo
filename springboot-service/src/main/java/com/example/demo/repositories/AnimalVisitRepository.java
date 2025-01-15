package com.example.demo.repositories;

import com.example.demo.entities.AnimalVisit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalVisitRepository extends JpaRepository<AnimalVisit, Long> {
}