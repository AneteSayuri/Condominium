package com.ada.Condominium.service;

import com.ada.Condominium.entity.Resident;
import com.ada.Condominium.entity.Unit;
import com.ada.Condominium.repository.UnitRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UnitServiceTest {
    @Mock
    private UnitRepository unitRepository;
    @InjectMocks
    private UnitService unitService;

    @Test
    @DisplayName("Save New Unit")
    void saveNewUnit() {
        var unit = new Unit(1L, "Apto1");

        when(unitRepository.save(unit)).thenReturn(unit);

        var savedUnit = unitService.save(unit);

        assertEquals(unit, savedUnit);
    }

    @Test
    @DisplayName("List All Units")
    void listAllUnits() {
        var unit1 = new Unit(1L, "Apto1");
        var unit2 = new Unit(2L, "Apto2");
        var unit3 = new Unit(3L, "Apto3");
        List<Unit> list = new ArrayList<Unit>();
        list.add(unit1);
        list.add(unit2);
        list.add(unit3);

        when(unitRepository.findAll()).thenReturn(list);
        assertTrue(unitService.findAll().size() == 3);
    }
}
