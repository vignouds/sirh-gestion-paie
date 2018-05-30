package dev.paie.web.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.paie.entite.Cotisation;
import dev.paie.exception.ItemNotFoundException;
import dev.paie.repository.CotisationRepository;

@RestController
@RequestMapping("/api/cotisations")
public class CotisationApiController {

	@Autowired
	CotisationRepository cRepo;

	@RequestMapping(method = RequestMethod.GET)
	public List<Cotisation> findCotisations() {
		return cRepo.findAll();
	}

	@RequestMapping(value = "/{cotisationCode}", method = RequestMethod.GET)
	public Cotisation findCotisationByCode(@PathVariable String cotisationCode) throws ItemNotFoundException {

		if (cRepo.findByCode(cotisationCode) == null) {
			throw new ItemNotFoundException();
		}

		return cRepo.findByCode(cotisationCode);
	}

	@RequestMapping(method = RequestMethod.POST)
	public void insertCotisation(@RequestBody Cotisation cotisation) {
		cRepo.save(cotisation);
	}

	@RequestMapping(value = "/{cotisationCode}", method = RequestMethod.PUT)
	public void majCotisation(@PathVariable String cotisationCode, @RequestBody Cotisation cotisation)
			throws ItemNotFoundException {

		if (cRepo.findByCode(cotisationCode) == null) {
			throw new ItemNotFoundException();
		}

		cotisation.setId(cRepo.findByCode(cotisationCode).getId());
		cRepo.save(cotisation);
	}

	@RequestMapping(value = "/{cotisationCode}", method = RequestMethod.DELETE)
	public void deleteCotisation(@PathVariable String cotisationCode)
			throws ItemNotFoundException, DataIntegrityViolationException {

		if (cRepo.findByCode(cotisationCode) == null) {
			throw new ItemNotFoundException();
		}

		cRepo.delete(cRepo.findByCode(cotisationCode));
	}
}
