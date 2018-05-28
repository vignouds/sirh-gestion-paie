package dev.paie.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.ProfilRemuneration;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.ProfilRemunerationRepository;

@Controller
@RequestMapping("/employes")
public class RemunerationEmployeController {

	@Autowired
	EntrepriseRepository eRepo;
	@Autowired
	ProfilRemunerationRepository prRepo;
	@Autowired
	GradeRepository gRepo;

	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	public String creerEmploye(Model model) {
		RemunerationEmploye remuneration = new RemunerationEmploye();
		model.addAttribute("remuneration", remuneration);

		List<Entreprise> entreprises = eRepo.findAll();
		model.addAttribute("entreprises", entreprises);

		List<ProfilRemuneration> profils = prRepo.findAll();
		model.addAttribute("profils", profils);

		List<Grade> grades = gRepo.findAll();
		model.addAttribute("grades", grades);

		return "employes/creerEmploye";
	}

	/*
	 * @RequestMapping(method = RequestMethod.POST) public String
	 * submitForm(@ModelAttribute("remuneration") RemunerationEmploye
	 * remuneration) { clientService.make(client); return "clientSuccess"; }
	 */
}
