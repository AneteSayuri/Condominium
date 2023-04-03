package com.ada.Condominium.controller;

import com.ada.Condominium.entity.Resident;
import com.ada.Condominium.service.ResidentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/resident")
// http://localhost:8080/resident

public class ResidentController {
    private final ResidentService residentService;

    public ResidentController(ResidentService residentService) {
        this.residentService = residentService;
    }

    @GetMapping
    public List<Resident> findAll() {
        return residentService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Resident findAll(@PathVariable Long id) {
        return residentService.findById(id);
    }

    @GetMapping(value = "/unit/{id}")
    // http://localhost:8080/resident/unit/{id}
    public List<Resident> listResidentsByUnitID (@PathVariable Long id) {
        return residentService.listResidentsByUnitID(id);
    }

    @PostMapping
    public Resident save(@RequestBody Resident resident) {
        Resident savedResident = null;
        try {
           savedResident = residentService.save(resident);
        } catch (IllegalArgumentException exception){
            System.out.println("Already registered Resident.");
        }
        return savedResident;
    }
}
