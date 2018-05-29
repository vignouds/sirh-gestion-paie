package dev.paie.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.entite.Cotisation;

@Service
public class CotisationServiceJpa implements CotisationService {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public void sauvegarder(Cotisation nouvelleCotisation) {
		em.persist(nouvelleCotisation);
	}

	@Override
	@Transactional
	public void mettreAJour(Cotisation cotisation) {
		Cotisation cotisation1 = em.find(Cotisation.class, cotisation.getId());
		if (cotisation != null) {
			cotisation1.setCode("nouveau code");
		}
	}

	@Override
	@Transactional
	public List<Cotisation> lister() {
		Query query = em.createQuery("from Cotisation");
		List<Cotisation> cotisations = query.getResultList();
		return cotisations;
	}

	@Override
	@Transactional
	public void supprimer() {
		em.createQuery("DELETE FROM Cotisation").executeUpdate();

	}
}
