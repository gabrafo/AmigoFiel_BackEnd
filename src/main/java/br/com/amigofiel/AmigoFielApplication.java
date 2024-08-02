package br.com.amigofiel;

import br.com.amigofiel.domain.entities.Animal;
import br.com.amigofiel.mappers.AnimalMapper;
import br.com.amigofiel.repositories.AnimalRepository;
import br.com.amigofiel.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.List;

@SpringBootApplication
@EnableFeignClients(basePackages = "br.com.amigofiel.clients")
public class AmigoFielApplication {

	public static void main(String[] args) {

		SpringApplication.run(AmigoFielApplication.class, args);
	}

}
