package dev.paie.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan({ "dev.paie.service", "dev.paie.util" })
@Import(JpaConfig.class)

@EnableTransactionManagement
@EnableJpaRepositories("dev.paie.repository")
public class ServicesConfig {

}
