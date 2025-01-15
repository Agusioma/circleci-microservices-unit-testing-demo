package com.example.demo.services;

import com.example.demo.entities.AnimalVisit;
import com.example.demo.repositories.AnimalVisitRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimalVisitService {

    @Autowired
    private AnimalVisitRepository repository;

    @Autowired
    private ModelMapper mapper;

    public void saveVisit(AnimalVisit visit) {
        repository.save(visit);
    }

    public List<AnimalVisit> getAllVisits() {
        List<AnimalVisit> allVisits = repository.findAll();
        return allVisits.stream()
                .map(it -> mapper.map(it, AnimalVisit.class))
                .collect(Collectors.toList());
    }
}