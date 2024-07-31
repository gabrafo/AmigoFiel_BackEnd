package br.com.amigofiel.domain.entities;
import br.com.amigofiel.domain.enums.CurrentStatus;
import br.com.amigofiel.domain.enums.Size;
import br.com.amigofiel.domain.enums.Specie;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

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
    private Specie specie;

    @Column(name = "breed")
    private String breed;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "sex")
    private char sex;

    @Column(name = "weight")
    private double weight;

    @Column(name = "size", nullable = false)
    @Enumerated(EnumType.STRING)
    private Size size;

    @Column(name = "neutered", nullable = false)
    private boolean neutered; // Castração

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Column(name = "registration_date", nullable = false) // Data que o animal foi postado
    private Date registrationDate;

    @Column(name = "current_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private CurrentStatus currentStatus;

    @OneToMany(mappedBy = "animalId", cascade = CascadeType.ALL)
    private List<Vaccin> vaccins;

    @OneToOne(mappedBy = "animal")
    private Adoption adoption;

    public Animal(String name, Specie specie, String breed, Date birthDate, char sex, double weight, Size size, boolean neutered,
                  Address address, Date registrationDate, CurrentStatus currentStatus, List<Vaccin> vaccins, Adoption adoption) {
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
        this.vaccins = vaccins;
        this.adoption = adoption;
    }
}
