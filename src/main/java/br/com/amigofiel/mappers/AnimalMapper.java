package br.com.amigofiel.mappers;

import br.com.amigofiel.domain.dto.AnimalDTO;
import br.com.amigofiel.domain.entities.Animal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnimalMapper {

    AnimalDTO toDTO(Animal animal);
    Animal toEntity(AnimalDTO animalDTO);
}
