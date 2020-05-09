package org.tfgdp2.com.domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Premio_Participante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String nombrePremio;

	@ManyToMany(mappedBy = "premio")
	private Collection<Nominacion_Participante> premiados;

	@ManyToMany
	private Collection<Gala> tiene;

	// ==========================================

	public Premio_Participante() {
		super();
		this.premiados = new ArrayList<Nominacion_Participante>();

	}

	public Premio_Participante(String nombrePremio) {
		super();
		this.nombrePremio = nombrePremio;
		this.premiados = new ArrayList<Nominacion_Participante>();
	}

	// ==================================

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombrePremio() {
		return nombrePremio;
	}

	public void setNombrePremio(String nombrePremio) {
		this.nombrePremio = nombrePremio;
	}

	public Collection<Nominacion_Participante> getPremiados() {
		return premiados;
	}

	public void setPremiados(Collection<Nominacion_Participante> premiados) {
		this.premiados = premiados;
	}

	public Collection<Gala> getTiene() {
		return tiene;
	}

	public void setTiene(Collection<Gala> tiene) {
		this.tiene = tiene;
	}

	

}
