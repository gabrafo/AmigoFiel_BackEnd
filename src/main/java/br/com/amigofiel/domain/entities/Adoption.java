package br.com.amigofiel.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class Adoption {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinColumn(name = "adoption_id", nullable = false)
    private List<Animal> adoptedsAnimals;

    @ManyToOne
    @JoinColumn(name = "adopter_id", nullable = false)
    private Adoptant adopter;

    @OneToOne
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;
}
