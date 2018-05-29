package dev.paie.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dev.paie.entite.BulletinSalaire;
import dev.paie.repository.PeriodeRepository;
import dev.paie.repository.RemunerationEmployeRepository;

@Controller
@RequestMapping("/bulletins")
public class BulletinSalaireController {

	@Autowired
	PeriodeRepository pRepo;

	@Autowired
	RemunerationEmployeRepository reRepo;

	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	public String creerBulletin(Model model) {
		BulletinSalaire bulletinSalaire = new BulletinSalaire();
		model.addAttribute("bulletin", bulletinSalaire);

		model.addAttribute("periodes", pRepo.findAll());

		model.addAttribute("remunerations", reRepo.findAll());

		return "bulletins/creerBulletin";
	}

}
