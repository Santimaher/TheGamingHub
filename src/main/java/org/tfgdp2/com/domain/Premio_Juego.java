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
public class Premio_Juego {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String nombrePremio;

	@OneToMany(mappedBy = "premio")
	private Collection<Nominacion_Juego> premiados;

	@ManyToOne
	private Gala tiene;

	// =====================================

	public Premio_Juego() {
		super();
		this.premiados = new ArrayList<>();
	}

	public Premio_Juego(String nombrePremio) {
		super();
		this.nombrePremio = nombrePremio;
		this.premiados = new ArrayList<>();
	}

	// =====================================

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

	public Collection<Nominacion_Juego> getPremiados() {
		return premiados;
	}

	public void setPremiados(Collection<Nominacion_Juego> premiados) {
		this.premiados = premiados;
	}

	public Gala getTiene() {
		return tiene;
	}

	public void setTiene(Gala tiene) {
		this.tiene = tiene;
	}

	

}
