package com.example.demo.controllers;

import com.example.demo.entities.AnimalVisit;
import com.example.demo.services.AnimalVisitService;
import com.example.demo.services.LogServiceInterceptorHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AnimalVisitControllerTest {

    @Mock
    private AnimalVisitService animalVisitService;

    @Mock
    private LogServiceInterceptorHelper logServiceInterceptorHelper;

    @InjectMocks
    private AnimalVisitController animalVisitController;

    private AutoCloseable mocks;

    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @AfterEach
    void tearDown() throws Exception {
        if (mocks != null) {
            mocks.close(); // Clean up mocks after each test
        }
    }

    @Test
    void testGetAllVisits_ReturnsListOfVisits() {
        // Arrange
        AnimalVisit visit = new AnimalVisit();
        visit.setId(1L);
        visit.setAnimalName("Buddy");
        visit.setReason("Check-up");
        visit.setVisitDate(LocalDate.now());

        when(animalVisitService.getAllVisits()).thenReturn(Collections.singletonList(visit));

        // Act
        ResponseEntity<List<AnimalVisit>> response = animalVisitController.getAllVisits();

        // Assert
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
        assertEquals("Buddy", response.getBody().get(0).getAnimalName());
    }

    @Test
    void testAddVisit_RedirectsAndLogsVisit() {
        // Arrange
        AnimalVisit visit = new AnimalVisit();
        visit.setAnimalName("Buddy");
        visit.setReason("Vaccination");
        visit.setVisitDate(LocalDate.now());

        // Mock the service calls
        //animalVisitService.saveVisit(visit);//.thenReturn(visit);

        // Act
        String viewName = animalVisitController.addVisit(visit);

        // Assert
        assertEquals("redirect:/visits", viewName);
        verify(animalVisitService).saveVisit(visit); // Verify saveVisit was called
        verify(logServiceInterceptorHelper).addLog("INFO: New animal visit added: Teddy"); // Verify log was added
    }
}
