package com.project.java_challenge.repositories;

import com.project.java_challenge.models.PointOfSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointOfSaleRepository extends JpaRepository<PointOfSale, Integer> {
}
