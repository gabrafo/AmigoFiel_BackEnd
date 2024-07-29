package br.com.amigofiel.domain.dto;

import br.com.amigofiel.domain.entities.Address;
import br.com.amigofiel.domain.entities.Adoption;
import br.com.amigofiel.domain.entities.Vaccin;
import br.com.amigofiel.domain.enums.CurrentStatus;
import br.com.amigofiel.domain.enums.Size;
import br.com.amigofiel.domain.enums.Specie;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;
import java.util.List;

public record AnimalDTO(
        String name,
        @NotNull Specie specie,
        String breed,
        Date birthDate,
        char sex,
        double weight,
        @NotNull Size size,
        @NotNull boolean neutered,
        @NotNull Address address,
        @NotNull Date registrationDate,
        @NotNull CurrentStatus currentStatus,
        List<Vaccin> vaccins,
        Adoption adoption
        ){
}
