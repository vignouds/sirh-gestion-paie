package dev.paie.entite;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "profile_remuneration")
public class ProfilRemuneration {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "CODE")
	private String code;

	@ManyToMany
	@JoinTable(name = "PRO_COTI", joinColumns = @JoinColumn(name = "ID_PRO", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "ID_COTI", referencedColumnName = "id"))
	private List<Cotisation> cotisationsNonImposables;

	@ManyToMany
	@JoinTable(name = "PRO_COTNOI", joinColumns = @JoinColumn(name = "ID_PRO", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "ID_COTNOI", referencedColumnName = "id"))
	private List<Cotisation> cotisationsImposables;

	@ManyToMany
	@JoinTable(name = "PRO_AVA", joinColumns = @JoinColumn(name = "ID_PRO", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "ID_AVA", referencedColumnName = "id"))
	private List<Avantage> avantages;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Cotisation> getCotisationsNonImposables() {
		return cotisationsNonImposables;
	}

	public void setCotisationsNonImposables(List<Cotisation> cotisationsNonImposables) {
		this.cotisationsNonImposables = cotisationsNonImposables;
	}

	public List<Cotisation> getCotisationsImposables() {
		return cotisationsImposables;
	}

	public void setCotisationsImposables(List<Cotisation> cotisationsImposables) {
		this.cotisationsImposables = cotisationsImposables;
	}

	public List<Avantage> getAvantages() {
		return avantages;
	}

	public void setAvantages(List<Avantage> avantages) {
		this.avantages = avantages;
	}

	public String toString() {
		return code;
	}

}
