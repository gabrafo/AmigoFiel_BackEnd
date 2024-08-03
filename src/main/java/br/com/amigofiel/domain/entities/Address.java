package br.com.amigofiel.domain.entities;

import br.com.amigofiel.domain.dto.AddressDTO;
import br.com.amigofiel.domain.enums.FederalUnit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "zip_code", nullable = false)
    private String zipCode;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "neighbourhood", nullable = false)
    private String neighbourhood;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "federal_unit", nullable = false)
    @Enumerated(EnumType.STRING)
    private FederalUnit federalUnit;

    public Address(AddressDTO dto){
        this.zipCode = dto.zipCode();
        this.street = dto.street();
        this.neighbourhood = dto.neighbourhood();
        this.city = dto.city();
        this.federalUnit = FederalUnit.valueOf(dto.federalUnit());
    }
}
