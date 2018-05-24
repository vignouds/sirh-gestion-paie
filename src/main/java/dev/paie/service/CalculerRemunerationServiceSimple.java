package dev.paie.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.util.PaieUtils;

@Service
public class CalculerRemunerationServiceSimple implements CalculerRemunerationService {

	@Autowired PaieUtils paieUtils;
	
	@Override
	public ResultatCalculRemuneration calculer(BulletinSalaire bulletin) {
		ResultatCalculRemuneration resultat = new ResultatCalculRemuneration();
		
		BigDecimal salaireDeBase = bulletin.getRemunerationEmploye().getGrade().getNbHeuresBase().multiply(bulletin.getRemunerationEmploye().getGrade().getTauxBase());
		String salaireDeBaseString = paieUtils.formaterBigDecimal(salaireDeBase);
		resultat.setSalaireDeBase(salaireDeBaseString);
		
		BigDecimal salaireBrut = salaireDeBase.add(bulletin.getPrimeExceptionnelle());
		String salaireBrutString = paieUtils.formaterBigDecimal(salaireBrut);
		resultat.setSalaireBrut(salaireBrutString);
//		SALAIRE_BRUT = SALAIRE_BASE + PRIME_EXCEPTIONNELLE
//		TOTAL_RETENUE_SALARIALE = SOMME(COTISATION_NON_IMPOSABLE.TAUX_SALARIAL*SALAIRE_BRUT)
//		TOTAL_COTISATIONS_PATRONALES = SOMME(COTISATION_NON_IMPOSABLE.TAUX_PATRONAL*SALAIRE_BRUT)
//
//		NET_IMPOSABLE = SALAIRE_BRUT - TOTAL_RETENUE_SALARIALE
//
//		NET_A_PAYER = NET_IMPOSABLE - SOMME(COTISATION_IMPOSABLE.TAUX_SALARIAL*SALAIRE_BRUT)		
				
				
		return resultat;
	}
}
