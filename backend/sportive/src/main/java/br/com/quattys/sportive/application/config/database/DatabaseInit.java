package br.com.quattys.sportive.application.config.database;

import br.com.quattys.sportive.business.entity.Address;
import br.com.quattys.sportive.resource.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseInit implements CommandLineRunner {

    private final AddressRepository addressRepository;

    @Autowired
    public DatabaseInit(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Address address = Address.builder()
                .street("Rua Antonio cirilo gomes")
                .number("254")
                .city("Campina Grande")
                .state("PB")
                .zipCode("58415-563")
                .build();

        addressRepository.save(address);

    }
}
