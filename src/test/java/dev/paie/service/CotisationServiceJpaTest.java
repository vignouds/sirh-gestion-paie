package dev.paie.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.H2Config;
import dev.paie.config.JpaConfig;
import dev.paie.config.ServicesConfig;
import dev.paie.entite.Cotisation;

//Sélection des classes de configuration Spring à utiliser lors du test
@ContextConfiguration(classes = { H2Config.class, JpaConfig.class, ServicesConfig.class })
// Configuration JUnit pour que Spring prenne la main sur le cycle de vie du
// test
@RunWith(SpringRunner.class)
public class CotisationServiceJpaTest {
	@Autowired
	private CotisationService cotisationService;

	private Cotisation cotisation1 = new Cotisation();

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
		cotisation1.setId(1);
		cotisation1.setCode("code");
		cotisation1.setLibelle("libelle");
		cotisation1.setTauxPatronal(new BigDecimal("0.55"));
		cotisation1.setTauxSalarial(new BigDecimal("0.15"));

		cotisationService.sauvegarder(cotisation1);

		assertThat(cotisationService.lister().contains(cotisation1));

		cotisationService.mettreAJour(cotisation1);

		assertThat(cotisationService.lister().contains(cotisation1));
	}
}
