package br.com.amigofiel.domain.dto;

import jakarta.validation.constraints.NotNull;

public record LoginDTO (
        @NotNull String username,
        @NotNull String password
){
}
