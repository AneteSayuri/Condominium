package com.ada.Condominium.service;

import com.ada.Condominium.entity.Resident;
import com.ada.Condominium.repository.ResidentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResidentService {
    private final ResidentRepository residentRepository;

    public ResidentService(ResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
    }

    public Resident save(Resident resident) {
        boolean residentFound = residentRepository.existsByName(resident.getName());

        if (residentFound) {
            throw new IllegalArgumentException("Already registered Resident: " + resident.getName());
        }

        return residentRepository.save(resident);
    }

    public List<Resident> findAll(){
        return (List<Resident>) residentRepository.findAll();
    }

    public Resident findById(Long id) {
        return residentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Resident not found by ID."));
    }

    public List<Resident> listResidentsByUnitID(Long id){
        return (List<Resident>) residentRepository.findByUnit_Id(id);
    }

}
