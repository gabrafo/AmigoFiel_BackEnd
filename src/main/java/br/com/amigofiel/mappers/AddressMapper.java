package br.com.amigofiel.mappers;

import br.com.amigofiel.domain.dto.AddressDTO;
import br.com.amigofiel.domain.dto.AdoptantDTO;
import br.com.amigofiel.domain.entities.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    Address toEntity(AddressDTO addressDTO);
    AdoptantDTO toDTO(Address address);
}
