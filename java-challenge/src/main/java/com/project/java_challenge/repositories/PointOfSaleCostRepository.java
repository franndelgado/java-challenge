package com.project.java_challenge.repositories;

import com.project.java_challenge.entities.PointOfSaleCost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PointOfSaleCostRepository  extends JpaRepository<PointOfSaleCost, Integer> {

    Optional<PointOfSaleCost> findByIdAAndIdB(Integer idA, Integer idB);
    Optional<PointOfSaleCost> findByIdBAndIdA(Integer idB, Integer idA);

    List<PointOfSaleCost> findByIdAOrIdB(Integer idA, Integer idB);
}
