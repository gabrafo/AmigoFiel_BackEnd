package br.com.amigofiel.mappers;

import br.com.amigofiel.domain.dto.AdoptantDTO;
import br.com.amigofiel.domain.entities.Adoptant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdoptantMapper {

    Adoptant toEntity(AdoptantDTO adoptantDTO);
    AdoptantDTO toDTO(Adoptant adoptant);
}
