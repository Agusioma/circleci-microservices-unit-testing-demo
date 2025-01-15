package com.example.demo.controllers;

import com.example.demo.entities.AnimalVisit;
import com.example.demo.services.AnimalVisitService;
import com.example.demo.services.LogServiceInterceptorHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AnimalVisitController {

    @Autowired
    private AnimalVisitService service;

    @Autowired
    private LogServiceInterceptorHelper logServiceInterceptorHelper;

    public AnimalVisitController(AnimalVisitService service, LogServiceInterceptorHelper logServiceInterceptorHelper) {
        this.service = service;
        this.logServiceInterceptorHelper = logServiceInterceptorHelper;
    }

    @GetMapping("/visits")
    public ResponseEntity<List<AnimalVisit>> getAllVisits() {
        List<AnimalVisit> visits = service.getAllVisits();
        return ResponseEntity.status(HttpStatus.OK).body(visits);
    }

    @GetMapping("/visits/new")
    public String newVisitForm(Model model) {
        model.addAttribute("visit", new AnimalVisit());
        return "new-visit";
    }

    @PostMapping("/visits")
    public String addVisit(AnimalVisit visit) {
        service.saveVisit(visit);
        //log
        logServiceInterceptorHelper.addLog("INFO: New animal visit added: " + visit.getAnimalName());

        return "redirect:/visits";
    }
}
