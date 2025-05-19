package com.project.java_challenge.repositories;

import com.project.java_challenge.models.Accreditation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccreditationRepository extends MongoRepository<Accreditation, String> {

}
