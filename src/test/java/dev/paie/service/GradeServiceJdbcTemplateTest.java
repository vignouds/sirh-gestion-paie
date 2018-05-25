package dev.paie.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.H2Config;
import dev.paie.config.JddConfig;
import dev.paie.entite.Grade;

//Sélection des classes de configuration Spring à utiliser lors du test
//@ContextConfiguration(classes = { ServicesConfig.class, H2Config.class, JddConfig.class })
@ContextConfiguration(classes = { GradeServiceJdbcTemplate.class, H2Config.class, JddConfig.class })
// Configuration JUnit pour que Spring prenne la main sur le cycle de vie du
// test
@RunWith(SpringRunner.class)

public class GradeServiceJdbcTemplateTest {

	@Autowired
	private Grade grade1;

	@Autowired
	private GradeService gradeService;

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
		grade1.setId(5);
		grade1.setCode("code");
		gradeService.sauvegarder(grade1);

		List<Grade> grades = gradeService.lister();

		assertThat(grades.contains(grade1));

		gradeService.mettreAJour(grade1);

		assertThat(grades.contains(grade1));
	}
}
