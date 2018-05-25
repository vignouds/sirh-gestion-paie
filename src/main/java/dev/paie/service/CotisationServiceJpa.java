package dev.paie.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CotisationServiceJpa implements CotisationService {

	@PersistenceContext
	private EntityManager em;
}
