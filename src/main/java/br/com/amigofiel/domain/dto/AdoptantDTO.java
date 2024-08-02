package br.com.amigofiel.domain.dto;
import br.com.amigofiel.domain.entities.Adoption;
import br.com.amigofiel.domain.entities.User;
import br.com.amigofiel.domain.enums.Gender;
import br.com.amigofiel.domain.entities.Address;
import jakarta.validation.constraints.NotNull;


import java.sql.Date;
import java.util.List;

public record AdoptantDTO(
        Long id,
        String name,
        Date birthDate,
        @NotNull Gender gender,
        @NotNull  Address address,
        List<Adoption> adoptions,
        @NotNull User user
) {
}
