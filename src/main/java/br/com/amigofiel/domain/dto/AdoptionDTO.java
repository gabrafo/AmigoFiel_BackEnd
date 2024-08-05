package br.com.amigofiel.domain.dto;

import br.com.amigofiel.domain.entities.Adoptant;
import br.com.amigofiel.domain.entities.Adoption;
import br.com.amigofiel.domain.entities.Animal;

public record AdoptionDTO (
        Long id,
        Adoptant adoptant,
        Animal animal){

    public AdoptionDTO(Adoption adoption) {
        this(
                adoption.getId(),
                adoption.getAdopter(),
                adoption.getAnimal()
        );
    }
}
