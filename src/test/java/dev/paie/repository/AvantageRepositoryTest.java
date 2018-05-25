package dev.paie.repository;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.DataSourceMySQLConfig;
import dev.paie.config.JpaConfig;
import dev.paie.config.ServicesConfig;
import dev.paie.entite.Avantage;

@ContextConfiguration(classes = { DataSourceMySQLConfig.class, JpaConfig.class, ServicesConfig.class })
@RunWith(SpringRunner.class)
public class AvantageRepositoryTest {

	private Avantage avantage1 = new Avantage();

	@Autowired
	private AvantageRepository avantageRepository;

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
		avantage1.setId(1);
		avantage1.setCode("code");
		avantage1.setNom("nom");
		avantage1.setMontant(new BigDecimal("1000"));

		avantageRepository.save(avantage1);

		assertEquals(avantage1.getCode(), avantageRepository.findOne(1).getCode());

		avantage1.setCode("nouveau code");
		avantageRepository.save(avantage1);

		assertEquals("nouveau code", avantageRepository.findOne(1).getCode());
	}
}
