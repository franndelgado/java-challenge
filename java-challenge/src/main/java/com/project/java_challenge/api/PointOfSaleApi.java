package com.project.java_challenge.api;

import com.project.java_challenge.dtos.PointOfSaleDTO;
import com.project.java_challenge.exceptions.PointOfSaleNotFoundException;
import com.project.java_challenge.models.PointOfSale;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface PointOfSaleApi {

    @Operation(summary = "Obtener la lista de todos los puntos de venta registrados",
            description = "Este endpoint devuelve una lista con todos los puntos de venta disponibles.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se obtuvo correctamente la lista de puntos de venta.",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = PointOfSale.class))
                    }
            )
    })
    ResponseEntity<List<PointOfSale>> getAllPointOfSale(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size);


    @Operation(summary = "Crear un nuevo punto de venta",
            description = "Crea un nuevo punto de venta a partir del nombre proporsionado y se le asigna un id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Punto de venta creado exitosamente.",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = PointOfSale.class))
                    }
            )
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del nuevo punto de venta", required = true,
            content = { @Content(mediaType = "application/json",
                    examples = @ExampleObject(name = "Ejemplo de punto de venta",
                            value = """
                                    {
                                        "name": "Tierra del fuego"
                                    }
                                    """
                    )
            )}
    )
    ResponseEntity<PointOfSale> addPointOfSale(@RequestBody PointOfSaleDTO pointOfSaleDTO);


    @Operation(summary = "Actualizar un punto de venta.",
            description = "Actualiza el nombre de un punto de venta identificado por el Id que recibe por parámetro.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Punto de venta actualizado correctamente",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = PointOfSale.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Punto de venta no encontrado.")
    })
    @Parameter(name = "id", description = "Id del punto de venta", required = true)
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Nuevo nombre del punto de venta",
            required = true,
            content = { @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = "\"Punto de venta actualizado\"")
            )}
    )
    ResponseEntity<PointOfSale> updatePointOfSale(@PathVariable int id, @RequestBody String name) throws PointOfSaleNotFoundException;


    @Operation(summary = "Eliminar un punto de venta segun su identificador.",
            description = "Elimina un punto de venta identificado por el Id que recibe por parámetro.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Punto de venta eliminado correctamente."),
            @ApiResponse(responseCode = "404", description = "No se encontró punto de venta.")
    })
    @Parameter(name = "id", description = "Id del punto de venta para eliminar", required = true)
    ResponseEntity<Void> deletePointOfSale(@PathVariable int id);
}
