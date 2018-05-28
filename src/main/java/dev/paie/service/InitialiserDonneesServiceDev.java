package dev.paie.service;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.entite.Cotisation;
import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.Periode;
import dev.paie.entite.ProfilRemuneration;

@Service
@Transactional
public class InitialiserDonneesServiceDev implements InitialiserDonneesService {

	@PersistenceContext
	private EntityManager em;

	private ClassPathXmlApplicationContext context;

	@Override
	public void initialiser() {
		context = new ClassPathXmlApplicationContext("cotisations-imposables.xml", "cotisations-non-imposables.xml",
				"entreprises.xml", "grades.xml", "profils-remuneration.xml");

		Collection<Cotisation> cotisations = context.getBeansOfType(Cotisation.class).values();
		for (Cotisation cotisation : cotisations) {
			em.persist(cotisation);
		}

		Collection<Entreprise> entreprises = context.getBeansOfType(Entreprise.class).values();
		for (Entreprise entreprise : entreprises) {
			em.persist(entreprise);
		}

		Collection<Grade> grades = context.getBeansOfType(Grade.class).values();
		for (Grade grade : grades) {
			em.persist(grade);
		}

		Collection<ProfilRemuneration> profils = context.getBeansOfType(ProfilRemuneration.class).values();
		for (ProfilRemuneration profilRemuneration : profils) {
			em.persist(profilRemuneration);
		}

		ArrayList<Periode> periodes = new ArrayList<>();
		int currentYear = LocalDate.now().getYear();
		for (int i = 1; i <= 12; i++) {
			Periode periode = new Periode();
			LocalDate debut = LocalDate.of(currentYear, i, 1);
			periode.setDateDebut(debut);
			periode.setDateFin(debut.with(lastDayOfMonth()));
			periodes.add(periode);
		}

		for (Periode periode : periodes) {
			em.persist(periode);
		}
	}

}
