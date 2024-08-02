package br.com.amigofiel.services;

import br.com.amigofiel.domain.dto.AdoptantDTO;
import br.com.amigofiel.domain.entities.Address;
import br.com.amigofiel.domain.entities.Adoptant;
import br.com.amigofiel.exceptions.NotFoundException;
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

    public void createAdoptant(AdoptantDTO adoptantDTO) {
        Adoptant newAdoptant = adoptantMapper.toEntity(adoptantDTO);
        adoptantRepository.save(newAdoptant);
    }

    public AdoptantDTO findAdoptantById(Long id) {
        return adoptantMapper.toDTO(adoptantRepository.findById(id).orElseThrow(() -> new NotFoundException("Adoptant not found")));
    }

    public List<Adoptant> findAllAdoptants() {
        return adoptantRepository.findAll();
    }

    public AdoptantDTO updateAdoptant(Adoptant adoptant){
        Optional<Adoptant> adoptantFound = adoptantRepository.findById(adoptant.getId());

        if (adoptantFound.isPresent()) {
            Adoptant existingAdoptant = adoptantFound.get();
            existingAdoptant.setName(adoptant.getName());
            existingAdoptant.setGender(adoptant.getGender());

            Address address = existingAdoptant.getAddress();

            address.setStreet(adoptant.getAddress().getStreet());
            address.setNeighbourhood(adoptant.getAddress().getNeighbourhood());
            address.setCity(adoptant.getAddress().getCity());
            address.setZipCode(adoptant.getAddress().getZipCode());

            return adoptantMapper.toDTO(adoptantRepository.save(existingAdoptant));
        }
        else {
            throw new RuntimeException("Adoptant not found.");
        }
    }

    public void deleteAdoptantById(AdoptantDTO adoptantDTO) {
        Adoptant newAdoptant = adoptantMapper.toEntity(adoptantDTO);
        adoptantRepository.deleteById(newAdoptant.getId());
    }
}
