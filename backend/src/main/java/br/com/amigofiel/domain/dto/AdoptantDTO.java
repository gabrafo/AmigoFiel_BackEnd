package br.com.amigofiel.domain.dto;
import br.com.amigofiel.domain.entities.Adoptant;
import br.com.amigofiel.domain.entities.Adoption;
import br.com.amigofiel.domain.entities.User;
import br.com.amigofiel.domain.enums.Gender;
import br.com.amigofiel.domain.entities.Address;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;


import java.sql.Date;
import java.util.List;

public record AdoptantDTO(
        String name,
        Date birthDate,
        @NotNull Gender gender,
        @NotNull  Address address,
        List<Adoption> adoptions,
        @NotNull User user
) {
    @Builder
    public AdoptantDTO(Adoptant adoptant) {
        this(
                adoptant.getName(),
                adoptant.getBirthDate(),
                adoptant.getGender(),
                adoptant.getAddress(),
                adoptant.getAdoptions(),
                adoptant.getUser()
        );
    }
}
