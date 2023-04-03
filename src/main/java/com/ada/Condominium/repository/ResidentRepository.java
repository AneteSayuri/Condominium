package com.ada.Condominium.repository;

import com.ada.Condominium.entity.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResidentRepository extends JpaRepository<Resident, Long> {
    boolean existsByName (String name);
    List<Resident> findByUnit_Id (Long id);

}
