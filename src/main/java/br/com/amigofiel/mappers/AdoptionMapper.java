package br.com.amigofiel.mappers;

import br.com.amigofiel.domain.dto.AdoptionDTO;
import br.com.amigofiel.domain.entities.Adoption;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdoptionMapper {
    Adoption toEntity(AdoptionDTO adoptionDTO);
    AdoptionDTO toDTO(Adoption adoption);
}
