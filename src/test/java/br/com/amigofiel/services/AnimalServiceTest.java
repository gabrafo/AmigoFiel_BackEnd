package br.com.amigofiel.services;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;
import static br.com.amigofiel.utils.AnimalConstants.ANIMAL;
import static br.com.amigofiel.utils.AnimalConstants.ANIMAL_DTO;

import br.com.amigofiel.domain.entities.Animal;
import br.com.amigofiel.mappers.AnimalMapper;
import br.com.amigofiel.repositories.AnimalRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AnimalServiceTest {

    @InjectMocks
    private AnimalService animalService;

    @Mock
    private AnimalMapper animalMapper;

    @Mock
    private AnimalRepository animalRepository;

    @Test
    public void createAnimal_WithValidData_ReturnsAnimal(){

        // Arrange
        when(animalRepository.save(ANIMAL)).thenReturn(ANIMAL);
        when(animalMapper.toDTO(ANIMAL)).thenReturn(ANIMAL_DTO);
        when(animalMapper.toEntity(ANIMAL_DTO)).thenReturn(ANIMAL);

        // Act
        Animal sut = animalService.create(animalMapper.toDTO(ANIMAL));

        // Assert
        assertThat(sut).isEqualTo(ANIMAL);
    }
}
