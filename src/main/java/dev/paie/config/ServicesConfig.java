package dev.paie.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ImportResource("jdd-config.xml")
@ComponentScan({ "dev.paie.service", "dev.paie.util" })
@EnableTransactionManagement
@EnableJpaRepositories("dev.paie.repository")
public class ServicesConfig {

}
