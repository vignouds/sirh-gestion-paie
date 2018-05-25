package dev.paie.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ImportResource("jdd-config.xml")
@ComponentScan({ "dev.paie.service", "dev.paie.util" })
@EnableTransactionManagement
public class ServicesConfig {

}
