package dev.paie.web.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dev.paie.entite.BulletinSalaire;
import dev.paie.repository.BulletinSalaireRepository;
import dev.paie.repository.PeriodeRepository;
import dev.paie.repository.RemunerationEmployeRepository;
import dev.paie.service.CalculerRemunerationService;

@Controller
@RequestMapping("/bulletins")
public class BulletinSalaireController {

	@Autowired
	PeriodeRepository pRepo;
	@Autowired
	RemunerationEmployeRepository reRepo;
	@Autowired
	BulletinSalaireRepository bsRepo;
	@Autowired
	CalculerRemunerationService remunerationService;

	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	public String creerBulletin(Model model) {
		BulletinSalaire bulletinSalaire = new BulletinSalaire();
		model.addAttribute("bulletin", bulletinSalaire);

		model.addAttribute("periodes", pRepo.findAll());

		model.addAttribute("remunerations", reRepo.findAll());

		return "bulletins/creerBulletin";
	}

	@RequestMapping(method = RequestMethod.POST, path = "/creer")
	public String submitForm(@ModelAttribute("bulletin") BulletinSalaire bulletinSalaire) {
		bulletinSalaire.setHeureCreation(LocalDateTime.now());
		bulletinSalaire.setHeureCreationFormat();
		bsRepo.save(bulletinSalaire);
		return "redirect:/mvc/bulletins/lister";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/lister")
	public String listerBulletin(Model model) {

		model.addAttribute("bulletins", remunerationService.bulletinCalcul());
		return "bulletins/listerBulletin";
	}
}
