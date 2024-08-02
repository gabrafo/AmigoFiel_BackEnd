package br.com.amigofiel.services;

import br.com.amigofiel.clients.ViaCepClient;
import br.com.amigofiel.domain.dto.AddressDTO;
import br.com.amigofiel.domain.entities.Address;
import br.com.amigofiel.exceptions.ClientException;
import br.com.amigofiel.exceptions.InvalidEntryException;
import br.com.amigofiel.mappers.AddressMapper;
import br.com.amigofiel.repositories.AddressRepository;
import feign.FeignException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AddressService {

    @Autowired
    private AddressRepository repository;

    @Autowired
    private ViaCepClient viaCepClient;

    @Autowired
    private Validator validator;

    @Autowired
    private AddressMapper addressMapper;

    public Address findAddress(String zip) {

        // Tira qualquer caractere que não for um número
        zip = zip.replaceAll("\\D", "");

        if (zip.length()!=8) throw new InvalidEntryException("CEP inválido");

        // Tenta fazer a requisição e se ela der certo salva o endereço no banco de dados e retorna os valores cadastrados
        try{
            AddressDTO addressDTO = viaCepClient.findAddressByZipCode(zip);

            // Valida o EnderecoDTO e retorna uma exceção com todos os campos inválidos
            Set<ConstraintViolation<AddressDTO>> violations = validator.validate(addressDTO);
            if (!violations.isEmpty()) {
                String errorMessage = violations.stream()
                        .map(ConstraintViolation::getMessage)
                        .collect(Collectors.joining(", "));
                throw new InvalidEntryException("Dados inválidos! " + errorMessage);
            }

            Address address = addressMapper.toEntity(addressDTO);
            repository.save(address);
            return address;
        } catch (HttpClientErrorException.BadRequest e){
            throw new InvalidEntryException("CEP não encontrado");
        } catch (FeignException.FeignClientException e){
            throw new ClientException("Erro ao consultar o CEP");
        }
    }
}
