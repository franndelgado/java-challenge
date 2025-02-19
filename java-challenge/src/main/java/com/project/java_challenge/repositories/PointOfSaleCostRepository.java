package com.project.java_challenge.repositories;

import com.project.java_challenge.entities.PointOfSaleCost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointOfSaleCostRepository  extends JpaRepository<PointOfSaleCost, Integer> {
}
