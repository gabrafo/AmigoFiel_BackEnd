package br.com.amigofiel.domain.entities;
import br.com.amigofiel.domain.enums.CurrentStatus;
import br.com.amigofiel.domain.enums.Size;
import br.com.amigofiel.domain.enums.Specie;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

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

    @Column(name = "column")
    private double weight;

    @Column(name = "size", nullable = false)
    @Enumerated(EnumType.STRING)
    private Size size;

    @Column(name = "neutered", nullable = false)
    private boolean neutered; // Castração

    @ManyToOne(cascade = CascadeType.ALL)
    @Column(name = "location_id", nullable = false)
    private Address location;

    @Column(name = "registration_date", nullable = false) // Data que o animal foi postado
    private Date registrationDate;

    @Column(name = "current_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private CurrentStatus currentStatus;

    @OneToMany
    @Column(name = "vaccins_list")
    private List<Vaccin> vaccins;
}
