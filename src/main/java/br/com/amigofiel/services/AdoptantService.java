package br.com.amigofiel.services;

import br.com.amigofiel.domain.dto.AdoptantDTO;
import br.com.amigofiel.domain.entities.Adoptant;
import br.com.amigofiel.mappers.AdoptantMapper;
import br.com.amigofiel.repositories.AdoptantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AdoptantService {

    private final AdoptantRepository adoptantRepository;
    private final AdoptantMapper adoptantMapper;

    public Adoptant createAdoptant(AdoptantDTO adoptantDTO) {
        Adoptant newAdoptant = adoptantMapper.toEntity(adoptantDTO);
        return adoptantRepository.save(newAdoptant);
    }

    public Optional<Adoptant> findAdoptantById(AdoptantDTO adoptantDTO) {
        Adoptant newAdoptant = adoptantMapper.toEntity(adoptantDTO);
        return adoptantRepository.findById(newAdoptant.getId());
    }
    public List<Adoptant> listAllAdoptants() {
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
