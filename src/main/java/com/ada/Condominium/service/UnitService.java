package com.ada.Condominium.service;

import com.ada.Condominium.entity.Unit;
import com.ada.Condominium.repository.UnitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitService {
    private final UnitRepository unitRepository;

    public UnitService(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    public Unit save(Unit unit) {
        return unitRepository.save(unit);
    }

    public List<Unit> findAll(){
        return (List<Unit>) unitRepository.findAll();
    }

}
