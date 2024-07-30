package br.com.amigofiel.services;

import br.com.amigofiel.domain.dto.AdoptantDTO;
import br.com.amigofiel.domain.entities.Adoptant;
import br.com.amigofiel.mappers.AdoptantMapper;
import br.com.amigofiel.repositories.AdoptantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AdoptantService {

    private final AdoptantRepository adoptantRepository;
    private final AdoptantMapper adoptantMapper;

    public Adoptant createAdoptant(AdoptantDTO adoptantDTO) {
        Adoptant newAdoptant = adoptantMapper.toEntity(adoptantDTO);
        return adoptantRepository.save(newAdoptant);
    }

}
