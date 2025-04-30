package com.project.java_challenge.api;

import com.project.java_challenge.dtos.MinimumCostPathResponseDTO;
import com.project.java_challenge.dtos.PointOfSaleCostDTO;
import com.project.java_challenge.exceptions.MinimumCostPathException;
import com.project.java_challenge.models.PointOfSaleCost;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CostApi {

    @Operation(summary = "Obtener una lista de todos los costos",
            description = "Este endpoint devuelve una lista con todos los costos disponibles.")
    ResponseEntity<List<PointOfSaleCost>> getAllCosts();

    @Operation(summary = "Crear un nuevo costo",
            description = "Crea un nuevo costo a partir del body que recibe.")
    ResponseEntity<?> addNewCost(@RequestBody PointOfSaleCostDTO costBody);

    @Operation(summary = "Eliminar un costo segun sus identificadores",
            description = "Elimina un costo identificado por sus identificadores idA y idB.")
    ResponseEntity<Void> deleteCost(@RequestParam int idA, @RequestParam int idB);

    @Operation(summary = "Obtener información de los puntos de venta y sus costos",
            description = "Recibe un identificador del un punto de venta y devuelve información sobre cuantos caminos tiene y sus costos.")
    ResponseEntity<String> newSearchPointOfSaleCost(@PathVariable int id);

    @Operation
    ResponseEntity<MinimumCostPathResponseDTO> getMinimumPath(@RequestParam int from, @RequestParam int to) throws MinimumCostPathException;
}