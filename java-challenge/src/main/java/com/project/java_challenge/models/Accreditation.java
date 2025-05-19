package com.project.java_challenge.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "accreditationV2")
@Data
@AllArgsConstructor
public class Accreditation {

    @Id
    private String id;

    private Double amount;
    private Integer idPointOfSale;
    private LocalDateTime receptionDate;
    private String pointOfSaleName;

    public Accreditation() {

    }
}
