package dev.paie.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("jdd-config.xml")
@ComponentScan({ "dev.paie.util" })
public class JddConfig {

}
