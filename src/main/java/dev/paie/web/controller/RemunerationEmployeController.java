package dev.paie.web.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import dev.paie.entite.Collegue;
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
	@Secured("ROLE_ADMINISTRATEUR")
	public String creerEmploye(Model model) {
		RemunerationEmploye remuneration = new RemunerationEmploye();
		model.addAttribute("remuneration", remuneration);
		model.addAttribute("entreprises", eRepo.findAll());
		model.addAttribute("profils", prRepo.findAll());
		model.addAttribute("grades", gRepo.findAll());

		RestTemplate rt = new RestTemplate();
		Collegue[] result = rt.getForObject("http://collegues-api.cleverapps.io/collegues", Collegue[].class);
		ArrayList<String> matricules = new ArrayList<>();
		for (Collegue collegue : result) {
			matricules.add(collegue.getMatricule());
		}

		model.addAttribute("matricules", matricules);

		return "employes/creerEmploye";
	}

	@RequestMapping(method = RequestMethod.POST, path = "/creer")
	@Secured("ROLE_ADMINISTRATEUR")
	public String submitForm(@ModelAttribute("remuneration") RemunerationEmploye remuneration) {
		remuneration.setHeureCreation(LocalDateTime.now());
		remuneration.setHeureCreationFormat();
		reRepo.save(remuneration);
		return "redirect:/mvc/employes/lister";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/lister")
	@Secured({ "ROLE_UTILISATEUR", "ROLE_ADMINISTRATEUR" })
	public String listerEmploye(Model model) {
		List<RemunerationEmploye> remunerations = reRepo.findAll();
		model.addAttribute("remunerations", remunerations);
		return "employes/listerEmploye";
	}
}
