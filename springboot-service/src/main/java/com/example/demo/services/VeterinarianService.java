package com.example.demo.services;

import com.example.demo.entities.Veterinarian;
import com.example.demo.repositories.VeterinarianRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VeterinarianService {

    @Autowired
    private VeterinarianRepository repository;

    @Autowired
    private ModelMapper mapper;

    public void saveVeterinarian(Veterinarian veterinarian) {
        repository.save(veterinarian);
    }

    public List<Veterinarian> getAllVeterinarians() {

        List<Veterinarian> allVets = repository.findAll();
        return allVets.stream()
                .map(it -> mapper.map(it, Veterinarian.class))
                .collect(Collectors.toList());

    }
}