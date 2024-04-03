package com.imoehl.payrollapi.config;

import com.imoehl.payrollapi.models.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.imoehl.payrollapi.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class LoadDatabase  {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository){
        return args -> {
            log.info("Preloading " + repository.save(new Employee("John Doe", "Teacher")));
            log.info ("Preloading " + repository.save(new Employee("Jane Smith", "Developer")));
            log.info("Preloading " + repository.save(new Employee("Adam Smith", "Cashier")));
            log.info ("Preloading " + repository.save(new Employee("John Johnson", "Salesperson")));
        };
    }
}
