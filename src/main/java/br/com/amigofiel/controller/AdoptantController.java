package br.com.amigofiel.controller;

import br.com.amigofiel.domain.dto.AdoptantDTO;
import br.com.amigofiel.domain.entities.Adoptant;
import br.com.amigofiel.services.AdoptantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/adoptants")
public class AdoptantController {
    private final AdoptantService adoptantService;

    public AdoptantController(AdoptantService adoptantService) {
        this.adoptantService = adoptantService;
    }

    @PostMapping("/adoptants")
    public ResponseEntity<String> saveAdoptant(@RequestBody AdoptantDTO adoptantDTO) {
        adoptantService.createAdoptant(adoptantDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Adoptant " + adoptantDTO.name() + " created successfully!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Adoptant> findAdoptantById(@PathVariable("id") Long id) {
        AdoptantDTO adoptantDTO = adoptantService.findAdoptantById(id);

        if (adoptantDTO != null) {
            Adoptant adoptant = new Adoptant();

            adoptant.setName(adoptantDTO.name());
            adoptant.setBirthDate(adoptantDTO.birthDate());
            adoptant.setGender(adoptantDTO.gender());
            adoptant.setAddress(adoptantDTO.address());
            adoptant.setAdoptions(adoptantDTO.adoptions());
            adoptant.setUser(adoptantDTO.user());

            return ResponseEntity.ok(adoptant);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("list_adoptants")
    public ResponseEntity<List<AdoptantDTO>> listAdoptants() {
        List<Adoptant> adoptants = adoptantService.findAllAdoptants();
        List<AdoptantDTO> adoptantDTOs = new ArrayList<>();

        if (!adoptants.isEmpty()) {
            for (Adoptant adoptant : adoptants) {
                adoptantDTOs.add(new AdoptantDTO(adoptant.getId(), adoptant.getName(),
                        adoptant.getBirthDate(), adoptant.getGender(),
                        adoptant.getAddress(), adoptant.getAdoptions(),
                        adoptant.getUser()));
            }
            return ResponseEntity.ok(adoptantDTOs);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/adoptants/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> deleteAdoptantById(@PathVariable("id") Long id) {
        AdoptantDTO adoptantDTO = adoptantService.findAdoptantById(id);

        if (adoptantDTO != null) {
            Adoptant adoptant = new Adoptant();
            adoptant.setId(adoptantDTO.id());
            adoptantService.deleteAdoptantById(adoptantDTO);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Adoptant " + adoptant.getName() + " deleted successfully!");
        } else {
            throw new RuntimeException("Not found!");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdoptantDTO> updateAdoptant(@RequestBody Adoptant adoptant) {
        try {
            AdoptantDTO updatedAdoptant = adoptantService.updateAdoptant(adoptant);
            return ResponseEntity.ok(updatedAdoptant);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
