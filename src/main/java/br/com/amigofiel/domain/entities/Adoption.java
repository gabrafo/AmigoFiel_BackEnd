package br.com.amigofiel.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class Adoption {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID adoptionId;

    private List<Animal> adoptedsPets;
}
