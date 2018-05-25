package dev.paie.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.entite.Cotisation;

@Service
@Transactional
public class CotisationServiceJpa implements CotisationService {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void sauvegarder(Cotisation nouvelleCotisation) {
		em.persist(nouvelleCotisation);
	}

	@Override
	public void mettreAJour(Cotisation cotisation) {
		Cotisation cotisation1 = em.find(Cotisation.class, cotisation.getId());
		if (cotisation != null) {
			cotisation1.setCode("nouveau code");
		}
	}

	@Override
	public List<Cotisation> lister() {
		Query query = em.createQuery("from Cotisation");
		List<Cotisation> cotisations = query.getResultList();
		return cotisations;
	}
}
