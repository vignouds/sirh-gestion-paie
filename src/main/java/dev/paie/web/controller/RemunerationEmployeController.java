package dev.paie.web.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dev.paie.entite.RemunerationEmploye;
import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.ProfilRemunerationRepository;
import dev.paie.repository.RemunerationEmployeRepository;

@Controller
@RequestMapping("/employes")
public class RemunerationEmployeController {

	@Autowired
	EntrepriseRepository eRepo;
	@Autowired
	ProfilRemunerationRepository prRepo;
	@Autowired
	GradeRepository gRepo;
	@Autowired
	RemunerationEmployeRepository reRepo;

	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	public String creerEmploye(Model model) {
		RemunerationEmploye remuneration = new RemunerationEmploye();
		model.addAttribute("remuneration", remuneration);
		model.addAttribute("entreprises", eRepo.findAll());
		model.addAttribute("profils", prRepo.findAll());
		model.addAttribute("grades", gRepo.findAll());

		return "employes/creerEmploye";
	}

	@RequestMapping(method = RequestMethod.POST, path = "/creer")
	public String submitForm(@ModelAttribute("remuneration") RemunerationEmploye remuneration) {
		remuneration.setHeureCreation(LocalDateTime.now());
		remuneration.setHeureCreationFormat();
		reRepo.save(remuneration);
		return "redirect:/mvc/employes/lister";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/lister")
	public String listerEmploye(Model model) {
		List<RemunerationEmploye> remunerations = reRepo.findAll();
		model.addAttribute("remunerations", remunerations);
		return "employes/listerEmploye";
	}
}
