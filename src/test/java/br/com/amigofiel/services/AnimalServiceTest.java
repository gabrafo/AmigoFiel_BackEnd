package br.com.amigofiel.services;

import static br.com.amigofiel.utils.AnimalConstants.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import br.com.amigofiel.domain.dto.AnimalDTO;
import br.com.amigofiel.domain.entities.Animal;
import br.com.amigofiel.exceptions.NotFoundException;
import br.com.amigofiel.mappers.AnimalMapper;
import br.com.amigofiel.repositories.AnimalRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AnimalServiceTest {

    @InjectMocks
    private AnimalService animalService;

    @Mock
    private AnimalMapper animalMapper;

    @Mock
    private AnimalRepository animalRepository;

    @Test
    public void createAnimalAnimal_WithValidData_ReturnsAnimal(){

        // Arrange
        when(animalRepository.save(ANIMAL)).thenReturn(ANIMAL);

        when(animalMapper.toDTO(ANIMAL)).thenReturn(ANIMAL_DTO);
        when(animalMapper.toEntity(ANIMAL_DTO)).thenReturn(ANIMAL);

        // Act
        Animal sut = animalService.createAnimal(animalMapper.toDTO(ANIMAL));

        // Assert
        assertThat(sut).isEqualTo(ANIMAL);
    }

    @Test
    public void createAnimalAnimal_WithInvalidData_ThrowsException(){

        // Arrange
        when(animalRepository.save(animalMapper.toEntity(INVALID_ANIMAL_DTO))).thenThrow(RuntimeException.class);

        // Act & Assert
        assertThatThrownBy(() -> animalService.createAnimal(INVALID_ANIMAL_DTO)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void findAnimalById_WithValidId_ReturnsAnimal(){

        // Arrange
        when(animalRepository.findById(anyLong())).thenReturn(Optional.of(ANIMAL));

        when(animalMapper.toDTO(ANIMAL)).thenReturn(ANIMAL_DTO);

        // Act
        AnimalDTO sut = animalService.findAnimalById(anyLong());

        // Assert
        assertThat(sut).isNotNull();
        assertThat(sut).isEqualTo(animalMapper.toDTO(ANIMAL));
    }

    @Test
    public void findAnimalById_WithInvalidId_ThrowsException(){

        // Act & Assert
        assertThatThrownBy(() -> animalService.findAnimalById(-1L)).isInstanceOf(NotFoundException.class);
    }

    @Test
    public void findAllAnimals_ReturnsAllAnimals(){

        // Arrange
        List<Animal> animals = new ArrayList<>()
        {
            {
                add(ANIMAL);
            }
        };

        when(animalRepository.findAll()).thenReturn(animals);

        when(animalMapper.toDTO(ANIMAL)).thenReturn(ANIMAL_DTO);

        // Act
        List<AnimalDTO> sut = animalService.findAllAnimals();

        // Assert
        assertThat(sut).containsExactly(animalMapper.toDTO(ANIMAL));
    }

    @Test
    public void findAllAnimals_ReturnsEmptyList(){

        // Arrange
        when(animalRepository.findAll()).thenReturn(List.of());

        // Act
        List<AnimalDTO> sut = animalService.findAllAnimals();

        // Assert
        assertThat(sut).isEmpty();
    }

    @Test
    public void deleteAnimalByIdAnimal_WithValidId_DoestNotThrowException(){

        // Act & Assert
        assertThatCode(() -> animalService.deleteAnimalById(1L)).doesNotThrowAnyException();
    }

    @Test
    public void deleteAnimalByIdAnimal_WithInvalidId_ThrowsException(){

        // Arrange
        doThrow(RuntimeException.class).when(animalRepository).deleteById(anyLong());

        // Act & Assert
        assertThatThrownBy(() -> animalService.deleteAnimalById(1L)).isInstanceOf(RuntimeException.class);
    }
}
