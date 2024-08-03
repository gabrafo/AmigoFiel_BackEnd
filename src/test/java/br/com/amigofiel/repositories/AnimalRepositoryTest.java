package br.com.amigofiel.repositories;

import br.com.amigofiel.domain.entities.Address;
import br.com.amigofiel.domain.entities.Animal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static br.com.amigofiel.utils.AnimalConstants.ANIMAL;
import static org.assertj.core.api.Assertions.assertThat;

// TESTE DE INTEGRAÇÃO COM BD
@DataJpaTest
public class AnimalRepositoryTest {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private TestEntityManager testEntityManager; // Interage com o BD sem usar o Repository, utilizamos ele pois queremos testar o Repository

    @AfterEach
    public void afterEach(){
        ANIMAL.setId(null);
        ANIMAL.setAddress(null);
    }

    @Test
    public void createAnimal_WithValidData_ReturnsAnimal() {

        Address address = ANIMAL.getAddress();
        if (address != null) {
            if (address.getId() != null) {
                address = testEntityManager.merge(address);
            } else {
                address = testEntityManager.persistAndFlush(address);
            }
            ANIMAL.setAddress(address);
        }

        Animal animal = animalRepository.save(ANIMAL);

        Animal sut = testEntityManager.find(Animal.class, ANIMAL.getId());

        assertThat(sut).isNotNull();
        assertThat(sut.getName()).isEqualTo(animal.getName());
        assertThat(sut.getSpecie()).isEqualTo(animal.getSpecie());
        assertThat(sut.getBreed()).isEqualTo(animal.getBreed());
        assertThat(sut.getBirthDate()).isEqualTo(animal.getBirthDate());
        assertThat(sut.getSex()).isEqualTo(animal.getSex());
        assertThat(sut.getWeight()).isEqualTo(animal.getWeight());
        assertThat(sut.getSize()).isEqualTo(animal.getSize());
        assertThat(sut.getAddress()).isEqualTo(animal.getAddress());
        assertThat(sut.getRegistrationDate()).isEqualTo(animal.getRegistrationDate());
        assertThat(sut.getCurrentStatus()).isEqualTo(animal.getCurrentStatus());
        assertThat(sut.getAdoption()).isEqualTo(animal.getAdoption());
    }

    
}
