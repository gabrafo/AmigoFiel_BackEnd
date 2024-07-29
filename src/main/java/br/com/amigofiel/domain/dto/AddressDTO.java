package br.com.amigofiel.domain.dto;

import br.com.amigofiel.domain.enums.FederalUnit;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddressDTO(
        @NotBlank String zipCode,
        @NotBlank String street,
        @NotBlank String neighbourhood,
        @NotBlank String city,
        @NotNull int houseNumber,
        @NotNull FederalUnit FU
        ) {
}
