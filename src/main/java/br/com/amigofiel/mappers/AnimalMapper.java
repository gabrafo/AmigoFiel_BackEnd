package br.com.amigofiel.mappers;

import br.com.amigofiel.domain.dto.AnimalDTO;
import br.com.amigofiel.domain.entities.Animal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AnimalMapper {

    @Mapping(target = "name", source = "animal.name")
    @Mapping(target = "specie", source = "animal.specie")
    @Mapping(target = "breed", source = "animal.breed")
    @Mapping(target = "birthDate", source = "animal.birthDate")
    @Mapping(target = "sex", source = "animal.sex")
    @Mapping(target = "weight", source = "animal.weight")
    @Mapping(target = "size", source = "animal.size")
    @Mapping(target = "neutered", source = "animal.neutered")
    @Mapping(target = "address", source = "animal.address")
    @Mapping(target = "registrationDate", source = "animal.registrationDate")
    @Mapping(target = "currentStatus", source = "animal.currentStatus")
    @Mapping(target = "adoption", source = "animal.adoption")
    AnimalDTO toDTO(Animal animal);

    @Mapping(target = "name", source = "animalDTO.name")
    @Mapping(target = "specie", source = "animalDTO.specie")
    @Mapping(target = "breed", source = "animalDTO.breed")
    @Mapping(target = "birthDate", source = "animalDTO.birthDate")
    @Mapping(target = "sex", source = "animalDTO.sex")
    @Mapping(target = "weight", source = "animalDTO.weight")
    @Mapping(target = "size", source = "animalDTO.size")
    @Mapping(target = "neutered", source = "animalDTO.neutered")
    @Mapping(target = "address", source = "animalDTO.address")
    @Mapping(target = "registrationDate", source = "animalDTO.registrationDate")
    @Mapping(target = "currentStatus", source = "animalDTO.currentStatus")
    @Mapping(target = "adoption", source = "animalDTO.adoption")
    Animal toEntity(AnimalDTO animalDTO);
}
