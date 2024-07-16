package com.example.demo.bootstrap;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.dao.DivisionRepository;
import com.example.demo.entities.Customer;
import com.example.demo.entities.Division;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BootStrapData implements CommandLineRunner {
    private final CustomerRepository customerRepository;
    private final DivisionRepository divisionRepository;

    public BootStrapData(CustomerRepository customerRepository, DivisionRepository divisionRepository) {
        this.customerRepository = customerRepository;
        this.divisionRepository = divisionRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (customerRepository.count() <= 1) {
            Division haw = new Division();
            haw.setId(52L);
            Division ida = new Division();
            ida.setId(11L);
            Division bc = new Division();
            bc.setId(62L);
            Division wal = new Division();
            wal.setId(102L);
            Division nir = new Division();
            nir.setId(104L);

            Customer john = new Customer("1234 Elm Street", "John", "Daughton", "123-456-7890", "12345", haw);
            Customer jane = new Customer("567 Willow Avenue", "Jane", "Smith", "555-123-4567", "54831", ida);
            Customer janice = new Customer("789 Maple Lane", "Janice", "Minapington", "135-687-1564", "65248", bc);
            Customer jenean = new Customer("101 Forest Road", "Jenean", "Flungery", "800-555-7890", "96435", wal);
            Customer jacob = new Customer("2468 Oak Drive", "Jacob", "Willburough", "653-541-6183", "72196", nir);


            customerRepository.save(john);
            customerRepository.save(jane);
            customerRepository.save(janice);
            customerRepository.save(jenean);
            customerRepository.save(jacob);
        }


    }
}
