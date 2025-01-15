package com.example.demo.controllers;

import com.example.demo.entities.Veterinarian;
import com.example.demo.services.LogServiceInterceptorHelper;
import com.example.demo.services.VeterinarianService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class VeterinarianControllerTest {

    @Mock
    private VeterinarianService veterinarianService;

    @Mock
    private LogServiceInterceptorHelper logServiceInterceptorHelper;

    @InjectMocks
    private VeterinarianController veterinarianController;

    private AutoCloseable mocks;

    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        if (mocks != null) {
            mocks.close();
        }
    }

    @Test
    void testGetAllVeterinarians_ReturnsListOfVeterinarians() {
        // Arrange
        Veterinarian vet = new Veterinarian();
        vet.setId(1L);
        vet.setName("Dr. Smith");
        vet.setSpecialization("Surgery");

        when(veterinarianService.getAllVeterinarians()).thenReturn(Collections.singletonList(vet));

        // Act
        ResponseEntity<List<Veterinarian>> response = veterinarianController.getAllVets();

        // Assert
        assertEquals(1, Objects.requireNonNull(response.getBody()).size());
        assertEquals("Dr. Smith", response.getBody().get(0).getName());
        verify(veterinarianService).getAllVeterinarians();
    }

    @Test
    void testAddVeterinarian_ReturnsSavedVeterinarian() {
        // Arrange
        Veterinarian vet = new Veterinarian();
        vet.setName("Dr. Smith");
        vet.setSpecialization("Surgery");

        //when(veterinarianService.addVeterinarian(vet)).thenReturn(vet);

        // Act
        String viewName = veterinarianController.addVeterinarian(vet);

        // Assert
        assertEquals("redirect:/veterinarians", viewName);
        verify(veterinarianService).saveVeterinarian(vet); // Verify saveVisit was called
        verify(logServiceInterceptorHelper).addLog("INFO: New veterinarian added: Dr. Smith"); // Verify log was added
    }
}