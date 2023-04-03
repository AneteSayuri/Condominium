package com.ada.Condominium.controller;

import com.ada.Condominium.entity.Unit;
import com.ada.Condominium.service.UnitService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/unit")
// http://localhost:8080/unit

public class UnitController {
    private final UnitService unitService;

    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }

    @GetMapping
    public List<Unit> findAll() {
        return unitService.findAll();
    }

    @PostMapping
    public Unit save(@RequestBody Unit unit) {
        return unitService.save(unit);
    }
}
