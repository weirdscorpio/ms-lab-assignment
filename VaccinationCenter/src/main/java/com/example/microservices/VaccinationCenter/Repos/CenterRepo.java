package com.example.microservices.VaccinationCenter.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.microservices.VaccinationCenter.Entity.VaccinationCenter;

@Repository
public interface CenterRepo extends JpaRepository<VaccinationCenter, Integer>{

}
