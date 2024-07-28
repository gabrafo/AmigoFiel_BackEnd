package br.com.amigofiel.domain.entities;

import br.com.amigofiel.domain.enums.FederalUnit;
import jakarta.persistence.*;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "neighbourhood", nullable = false)
    private String neighbourhood;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "house_number", nullable = false)
    private int houseNumber;

    @Column(name = "federal_unit", nullable = false)
    @Enumerated(EnumType.STRING)
    private FederalUnit FU;
}
