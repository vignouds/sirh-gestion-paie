package dev.paie.entite;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class BulletinSalaire {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	@OneToOne
	private RemunerationEmploye remunerationEmploye;
	@ManyToOne
	@JoinColumn(name = "PER_ID")
	private Periode periode;
	@Column(name = "PRIME_EXCEPTIONNELLE")
	private BigDecimal primeExceptionnelle;
	@Column(name = "HEURE_CREATION")
	private LocalDateTime heureCreation;
	@Column(name = "HEURE_CREATION_FORMAT")
	private String heureCreationFormat;

	public RemunerationEmploye getRemunerationEmploye() {
		return remunerationEmploye;
	}

	public void setRemunerationEmploye(RemunerationEmploye remunerationEmploye) {
		this.remunerationEmploye = remunerationEmploye;
	}

	public Periode getPeriode() {
		return periode;
	}

	public void setPeriode(Periode periode) {
		this.periode = periode;
	}

	public BigDecimal getPrimeExceptionnelle() {
		return primeExceptionnelle;
	}

	public void setPrimeExceptionnelle(BigDecimal primeExceptionnelle) {
		this.primeExceptionnelle = primeExceptionnelle;
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
