package br.com.amigofiel.domain.dto;

import br.com.amigofiel.domain.enums.AuthRole;
import jakarta.validation.constraints.NotNull;

public record UserDTO(
        @NotNull String email,
        @NotNull String username,
        @NotNull String password,
        @NotNull AuthRole role
) {
}
