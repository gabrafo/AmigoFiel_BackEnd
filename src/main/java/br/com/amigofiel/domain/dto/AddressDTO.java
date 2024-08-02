package br.com.amigofiel.domain.dto;

import br.com.amigofiel.domain.enums.FederalUnit;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddressDTO(
        @NotBlank @JsonProperty("cep") String zipCode,
        @NotBlank @JsonProperty("logradouro") String street,
        @NotBlank @JsonProperty("bairro") String neighbourhood,
        @NotBlank @JsonProperty("localidade") String city,
        @NotNull @JsonProperty("uf") String FU
        ) {
}
