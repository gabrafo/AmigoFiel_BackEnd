package br.com.amigofiel.services;

import br.com.amigofiel.domain.dto.AnimalDTO;
import br.com.amigofiel.domain.entities.Animal;
import br.com.amigofiel.mappers.AnimalMapper;
import br.com.amigofiel.repositories.AnimalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final AnimalMapper animalMapper;

    public Animal create(AnimalDTO animalDTO) {
        Animal newAnimal = animalMapper.toEntity(animalDTO);
        return animalRepository.save(newAnimal);
    }
}
