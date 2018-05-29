package dev.paie.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Cotisation;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.repository.BulletinSalaireRepository;
import dev.paie.util.PaieUtils;

@Service
@Transactional
public class CalculerRemunerationServiceSimple implements CalculerRemunerationService {

	@Autowired
	private PaieUtils paieUtils;

	@Autowired
	private BulletinSalaireRepository bulletinSalaireRepository;

	@Override
	public Map<BulletinSalaire, ResultatCalculRemuneration> bulletinCalcul() {
		Map<BulletinSalaire, ResultatCalculRemuneration> result = new HashMap<BulletinSalaire, ResultatCalculRemuneration>();

		List<BulletinSalaire> bulletinsSalaires = bulletinSalaireRepository.findAll();
		for (BulletinSalaire bulletinSalaire : bulletinsSalaires) {
			result.put(bulletinSalaire, calculer(bulletinSalaire));
		}

		return result;
	}

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

		// Net imposable
		BigDecimal netImposable = new BigDecimal(salaireBrutString)
				.subtract(new BigDecimal(totalRetenueSalarialeString));
		String netImposableString = paieUtils.formaterBigDecimal(netImposable);
		resultat.setNetImposable(netImposableString);
		// Net à payer
		List<Cotisation> cotisationsImposables = bulletin.getRemunerationEmploye().getProfilRemuneration()
				.getCotisationsImposables();
		BigDecimal sommeCot = cotisationsImposables.stream().filter(cotis -> cotis.getTauxSalarial() != null)
				.map(cotis -> cotis.getTauxSalarial().multiply(salaireBrut)).reduce(BigDecimal::add).get();
		BigDecimal netAPayer = netImposable.subtract(sommeCot);
		String netAPayerString = paieUtils.formaterBigDecimal(netAPayer);
		resultat.setNetAPayer(netAPayerString);

		return resultat;
	}
}
