package dev.paie.service;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

import java.time.LocalDate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.entite.Cotisation;
import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.Periode;
import dev.paie.entite.ProfilRemuneration;
import dev.paie.entite.Utilisateur;
import dev.paie.entite.Utilisateur.ROLES;

@Service
@Transactional
public class InitialiserDonneesServiceDev implements InitialiserDonneesService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void initialiser() {

		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("cotisations-imposables.xml",
				"cotisations-non-imposables.xml", "entreprises.xml", "grades.xml", "profils-remuneration.xml")) {

			Stream.of(Cotisation.class, Entreprise.class, Grade.class, ProfilRemuneration.class)
					.flatMap(classe -> context.getBeansOfType(classe).values().stream()).forEach(em::persist);

			IntStream.rangeClosed(1, 12).mapToObj(i -> {
				Periode periode = new Periode();
				periode.setDateDebut(LocalDate.of(LocalDate.now().getYear(), i, 1));
				periode.setDateFin(periode.getDateDebut().with(lastDayOfMonth()));
				return periode;
			}).forEach(em::persist);

			Utilisateur utilisateur1 = new Utilisateur("seb", this.passwordEncoder.encode("abcdef"), true,
					ROLES.ROLE_ADMINISTRATEUR);
			Utilisateur utilisateur2 = new Utilisateur("guiu", this.passwordEncoder.encode("123456"), true,
					ROLES.ROLE_UTILISATEUR);
			em.persist(utilisateur1);
			em.persist(utilisateur2);
		}
	}
}
