package br.com.amigofiel.controller;

import br.com.amigofiel.domain.dto.AdoptionDTO;
import br.com.amigofiel.domain.dto.AnimalDTO;
import br.com.amigofiel.domain.entities.Adoption;
import br.com.amigofiel.services.AdoptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adoptions")
@Tag(name = "Adoption", description = "Endpoints relacionados a Adoption")
public class AdoptionController {

    @Autowired
    private AdoptionService adoptionService;

    @Operation(summary = "Cria uma adoção",
            description = "Cria uma nova adoção no Banco de Dados.",
            tags = {"Adoção"},
            responses = {
                @ApiResponse(description = "Success", responseCode = "200",
                        content = @Content(schema = @Schema(implementation = AdoptionDTO.class))
                ),
                @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
        }
    )
    @PostMapping(value = "/create",
            produces = "application/json",
            consumes = "application/json")
    public ResponseEntity<Adoption> createAnimal(@Valid @RequestBody AdoptionDTO adoptionDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(adoptionService.doAdoption(adoptionDTO));
    }

    @Operation(summary = "Lista as adoções por adotante",
                description = "Lista as adoções por adotante no Banco de dados.",
                tags = {"Adoção"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = AnimalDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @GetMapping(value="/all",
            produces = "application/json")
    public ResponseEntity<List<AdoptionDTO>> getAllAnimals() {
        return ResponseEntity.status(HttpStatus.OK).body(adoptionService.findAllAdoptions());
    }


    @Operation(summary = "Busca uma adoção por ID",
            description = "Busca uma adoção no Banco de Dados usando o ID como parâmetro.",
            tags = {"Adoção"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = AnimalDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Forbidden", responseCode = "403", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    @GetMapping(value="/{id}",
            produces = "application/json")
    public ResponseEntity<AdoptionDTO> getAnimalById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(adoptionService.findAdoptionById(id));
    }
}
