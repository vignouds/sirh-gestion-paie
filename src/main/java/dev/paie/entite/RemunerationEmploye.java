package dev.paie.entite;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class RemunerationEmploye {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	@Column(name = "MATRICULE")
	private String matricule;
	@ManyToOne
	@JoinColumn(name = "ENT_ID")
	private Entreprise entreprise;
	@ManyToOne
	@JoinColumn(name = "PRO_ID")
	private ProfilRemuneration profilRemuneration;
	@ManyToOne
	@JoinColumn(name = "GRA_ID")
	private Grade grade;
	@Column(name = "HEURE_CREATION")
	private LocalDateTime heureCreation;
	@Column(name = "HEURE_CREATION_FORMAT")
	private String heureCreationFormat;

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	public ProfilRemuneration getProfilRemuneration() {
		return profilRemuneration;
	}

	public void setProfilRemuneration(ProfilRemuneration profilRemuneration) {
		this.profilRemuneration = profilRemuneration;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getHeureCreation() {
		return this.heureCreation;

	}

	public void setHeureCreation(LocalDateTime heureCreation) {
		this.heureCreation = heureCreation;
	}

	public String getHeureCreationFormat() {
		return this.heureCreationFormat;
	}

	public void setHeureCreationFormat() {
		this.heureCreationFormat = this.heureCreation.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:SS"));
	}
}
