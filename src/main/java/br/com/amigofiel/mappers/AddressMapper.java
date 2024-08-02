package br.com.amigofiel.mappers;

import br.com.amigofiel.domain.dto.AddressDTO;
import br.com.amigofiel.domain.entities.Address;
import br.com.amigofiel.domain.enums.FederalUnit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    @Mapping(source = "FU", target = "FU", qualifiedByName = "stringToFederalUnit")
    Address toEntity(AddressDTO addressDTO);

    AddressDTO toDTO(Address address);

    @Named("stringToFederalUnit")
    default FederalUnit stringToFederalUnit(String fu) {
        return FederalUnit.valueOf(fu);
    }
}
