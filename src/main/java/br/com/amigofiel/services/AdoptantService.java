package br.com.amigofiel.services;

import br.com.amigofiel.domain.dto.AdoptantDTO;
import br.com.amigofiel.domain.entities.Adoptant;
import br.com.amigofiel.exceptions.NotFoundException;
import br.com.amigofiel.mappers.AdoptantMapper;
import br.com.amigofiel.repositories.AdoptantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AdoptantService {

    private final AdoptantRepository adoptantRepository;
    private final AdoptantMapper adoptantMapper;

    public Adoptant createAdoptant(AdoptantDTO adoptantDTO) {
        Adoptant newAdoptant = adoptantMapper.toEntity(adoptantDTO);
        return adoptantRepository.save(newAdoptant);
    }

    public AdoptantDTO findAdoptantById(Long id) {
        return adoptantMapper.toDTO(adoptantRepository.findById(id).orElseThrow(() -> new NotFoundException("Adoptant not found")));
    }

    public List<Adoptant> findAllAdoptants() {
        return adoptantRepository.findAll();
    }
    /* TODO()
    public Adoptant updateAdoptant(Adoptant adoptant){
    } */

    public void deleteAdoptantById(AdoptantDTO adoptantDTO) {
        Adoptant newAdoptant = adoptantMapper.toEntity(adoptantDTO);
        adoptantRepository.deleteById(newAdoptant.getId());
    }
}
