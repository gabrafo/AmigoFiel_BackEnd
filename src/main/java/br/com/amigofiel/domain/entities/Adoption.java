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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "adopted_animal_id", nullable = false)
    private Animal adoptedAnimal;

    @ManyToOne
    @JoinColumn(name = "adopter_id", nullable = false)
    private Adoptant adopter;

    @OneToOne
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;
}
