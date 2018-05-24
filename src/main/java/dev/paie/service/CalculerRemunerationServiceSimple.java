package dev.paie.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Cotisation;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.util.PaieUtils;

@Service
public class CalculerRemunerationServiceSimple implements CalculerRemunerationService {

	@Autowired
	PaieUtils paieUtils;

	@Override
	public ResultatCalculRemuneration calculer(BulletinSalaire bulletin) {
		ResultatCalculRemuneration resultat = new ResultatCalculRemuneration();

		// Salaire de base
		BigDecimal salaireDeBase = bulletin.getRemunerationEmploye().getGrade().getNbHeuresBase()
				.multiply(bulletin.getRemunerationEmploye().getGrade().getTauxBase());
		String salaireDeBaseString = paieUtils.formaterBigDecimal(salaireDeBase);
		resultat.setSalaireDeBase(salaireDeBaseString);

		// Salaire brute
		BigDecimal salaireBrut = salaireDeBase.add(bulletin.getPrimeExceptionnelle());
		String salaireBrutString = paieUtils.formaterBigDecimal(salaireBrut);
		resultat.setSalaireBrut(salaireBrutString);

		// Total retenue salariale
		List<Cotisation> cotisationsNonImposables = bulletin.getRemunerationEmploye().getProfilRemuneration()
				.getCotisationsNonImposables();

		BigDecimal totalRetenueSalariale = cotisationsNonImposables.stream()
				.filter(cotis -> cotis.getTauxSalarial() != null)
				.map(cotis -> cotis.getTauxSalarial().multiply(salaireBrut)).reduce(BigDecimal::add).get();

		String totalRetenueSalarialeString = paieUtils.formaterBigDecimal(totalRetenueSalariale);
		resultat.setTotalRetenueSalarial(totalRetenueSalarialeString);

		// Total Cotis patronales
		BigDecimal totalCotisPatronales = cotisationsNonImposables.stream()
				.filter(cotis -> cotis.getTauxPatronal() != null)
				.map(cotis -> cotis.getTauxPatronal().multiply(salaireBrut)).reduce(BigDecimal::add).get();

		String totalCotisPatronalesString = paieUtils.formaterBigDecimal(totalCotisPatronales);
		resultat.setTotalCotisationsPatronales(totalCotisPatronalesString);

		// NET_IMPOSABLE = SALAIRE_BRUT - TOTAL_RETENUE_SALARIALE
		//
		// NET_A_PAYER = NET_IMPOSABLE -
		// SOMME(COTISATION_IMPOSABLE.TAUX_SALARIAL*SALAIRE_BRUT)

		return resultat;
	}
}
