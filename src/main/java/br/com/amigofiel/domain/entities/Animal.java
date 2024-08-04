package br.com.amigofiel.domain.entities;
import br.com.amigofiel.domain.enums.CurrentStatus;
import br.com.amigofiel.domain.enums.Size;
import br.com.amigofiel.domain.enums.Specie;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "specie", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Specie cannot be null")
    private Specie specie;

    @Column(name = "breed")
    private String breed;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "sex")
    private char sex;

    @Column(name = "weight")
    @Positive
    private double weight;

    @Column(name = "size", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Size cannot be null")
    private Size size;

    @Column(name = "neutered", nullable = false)
    @NotNull(message = "Neutering information cannot be null")
    private boolean neutered; // Castração

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false)
    @NotNull(message = "Address cannot be null")
    private Address address;

    @Column(name = "registration_date", nullable = false) // Data que o animal foi postado
    @NotNull(message = "Registration date cannot be null")
    private Date registrationDate;

    @Column(name = "current_status", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Current status cannot be null")
    private CurrentStatus currentStatus;

    @OneToOne
    @JoinColumn(name = "adoption_id")
    private Adoption adoption;

    public Animal(String name, Specie specie, String breed, Date birthDate, char sex, double weight, Size size, boolean neutered,
                  Address address, Date registrationDate, CurrentStatus currentStatus, Adoption adoption) {
        this.name = name;
        this.specie = specie;
        this.breed = breed;
        this.birthDate = birthDate;
        this.sex = sex;
        this.weight = weight;
        this.size = size;
        this.neutered = neutered;
        this.address = address;
        this.registrationDate = registrationDate;
        this.currentStatus = currentStatus;
        this.adoption = adoption;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(obj, this); // Compara os objetos e não endereços de memória
    }

}
