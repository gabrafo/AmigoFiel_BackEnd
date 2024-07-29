package br.com.amigofiel.utils;

import br.com.amigofiel.domain.dto.AnimalDTO;
import br.com.amigofiel.domain.entities.Address;
import br.com.amigofiel.domain.entities.Animal;
import br.com.amigofiel.domain.enums.CurrentStatus;
import br.com.amigofiel.domain.enums.FederalUnit;
import br.com.amigofiel.domain.enums.Size;
import br.com.amigofiel.domain.enums.Specie;

import java.sql.Date;
import java.util.List;

public class AnimalConstants {
   public static final Address ADDRESS = new Address(1L, "street", "neighbourhood", "city", 1, FederalUnit.MG);
   public static final Animal ANIMAL =  new Animal(
           1L,
           "name",
           Specie.DOG,
           "breed",
           Date.valueOf("2020-01-01"),
           'M',
           30.0,
           Size.MEDIUM,
           true,
           ADDRESS,
           Date.valueOf("2021-06-15"),
           CurrentStatus.AVAILABLE,
           List.of(), // Supondo que não há vacinas inicialmente
           null // Supondo que não há adoção inicialmente
   );
   public static final AnimalDTO ANIMAL_DTO = new AnimalDTO(
           "name",
           Specie.DOG,
           "breed",
           Date.valueOf("2020-01-01"),
           'M',
           30.0,
           Size.MEDIUM,
           true,
           ADDRESS,
           Date.valueOf("2021-06-15"),
           CurrentStatus.AVAILABLE,
           List.of(), // Supondo que não há vacinas inicialmente
           null // Supondo que não há adoção inicialmente
   );

}
