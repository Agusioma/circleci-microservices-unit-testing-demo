package com.example.demo.controllers;

import com.example.demo.entities.Veterinarian;
import com.example.demo.services.LogServiceInterceptorHelper;
import com.example.demo.services.VeterinarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class VeterinarianController {

    @Autowired
    private final VeterinarianService service;

    @Autowired
    private LogServiceInterceptorHelper logServiceInterceptorHelper;

    public VeterinarianController(VeterinarianService service, LogServiceInterceptorHelper logServiceInterceptorHelper) {
        this.service = service;
        this.logServiceInterceptorHelper = logServiceInterceptorHelper;
    }

    @GetMapping("/veterinarians")
    public ResponseEntity<List<Veterinarian>> getAllVets() {
        List<Veterinarian> vets = service.getAllVeterinarians();
        return ResponseEntity.status(HttpStatus.OK).body(vets);
    }

    @GetMapping("/veterinarians/new")
    public String newVeterinarianForm(Model model) {
        model.addAttribute("veterinarian", new Veterinarian());
        return "new-veterinarian";
    }

    @PostMapping("/veterinarians")
    public String addVeterinarian(Veterinarian veterinarian) {
        service.saveVeterinarian(veterinarian);
        //logs
        logServiceInterceptorHelper.addLog("INFO: New veterinarian added: " + veterinarian.getName());

        return "redirect:/veterinarians";
    }
}