package com.ada.Condominium.service;

import com.ada.Condominium.entity.Resident;
import com.ada.Condominium.entity.Unit;
import com.ada.Condominium.repository.ResidentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ResidentServiceTest {
    @Mock
    private ResidentRepository residentRepository;
    @InjectMocks
    private ResidentService residentService;

    @Test
    @DisplayName("Test Exception When Saving New Resident")
    void saveResidentWhenItAlreadyExists() {
        var resident = new Resident(1L, "Felix", "felix@email.com");

        when(residentRepository.existsByName(resident.getName())).thenReturn(true);

        var exception = assertThrows(IllegalArgumentException.class
                , () -> residentService.save(resident));

        assertTrue(exception.getMessage().contains(resident.getName()));
        // Verify if Exception message contains residents name

        verify(residentRepository, never()).save(resident);
        // Verify if resident is Never saved
    }

    @Test
    @DisplayName("Save New Resident Successfully")
    void saveNewResidentSuccessfully() {
        var resident = new Resident(1L, "Felix", "felix@email.com");

        when(residentRepository.existsByName(resident.getName())).thenReturn(false);
        when(residentRepository.save(resident)).thenReturn(resident);

        var savedResident = residentService.save(resident);

        assertEquals(resident.getEmail(), savedResident.getEmail());
        // Verify if saved email is equals to the given email

        verify(residentRepository, times(1)).save(resident);
        // Verify if new resident is saved only once
    }

    @Test
    @DisplayName("Find Resident By ID Successfully")
    void findResidentByIDSuccessfully() {
        var resident = new Resident(1L, "Felix", "felix@email.com");

        when(residentRepository.findById(1L)).thenReturn(Optional.of(resident));

        assertEquals(1L, residentService.findById(1L).getId());
    }

    @Test
    @DisplayName("Resident Not Found By ID")
    void residentNotFoundByID() throws IllegalArgumentException {

        Throwable throwable = assertThrows(IllegalArgumentException.class,
                () -> residentService.findById(any()));

        assertEquals("Resident not found by ID.",
                throwable.getMessage());
    }

    @Test
    @DisplayName("List All Residents")
    void findAllResidents() {
        var resident1 = new Resident(1L, "Felix", "felix@email.com");
        var resident2 = new Resident(2L, "Jhon", "jhon@email.com");
        List<Resident> list = new ArrayList<Resident>();
        list.add(resident1);
        list.add(resident2);

        when(residentRepository.findAll()).thenReturn(list);
        assertEquals(2, residentService.findAll().size());
    }

    @Test
    @DisplayName("List All Residents By Unit ID")
    void listResidentsByUnitID() {
        var unit1 = new Unit();
        unit1.setId(1L);
        unit1.setApartment("Apto1");

        var resident1 = new Resident(3L, "Felix", "felix@email.com");
        var resident2 = new Resident(4L, "Felix", "felix@email.com");
        resident1.setUnit(unit1);
        resident2.setUnit(unit1);

        List<Resident> unitList = new ArrayList<>();
        unitList.add(resident1);
        unitList.add(resident2);

        when(residentRepository.findByUnit_Id(1L)).thenReturn(unitList);
        assertTrue(residentService.listResidentsByUnitID(1L)
                .get(1).getUnit().getApartment() == "Apto1");
    }
}
