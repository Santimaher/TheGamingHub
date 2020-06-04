package org.tfgdp2.com.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

	@OneToMany(mappedBy = "premio")
	private List<Nominacion_Participante> premiados;

	@ManyToOne
	private Gala tiene;

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

	public void setPremiados(List<Nominacion_Participante> premiados) {
		this.premiados = premiados;
	}

	public Gala getTiene() {
		return tiene;
	}

	public void setTiene(Gala tiene) {
		this.tiene = tiene;
	}

	

}
