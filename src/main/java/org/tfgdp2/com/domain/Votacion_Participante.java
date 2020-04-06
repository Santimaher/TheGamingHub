package org.tfgdp2.com.domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Votacion_Participante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy = "votacion")
	private Collection<Nominacion_Participante> votaciones;
	
	@OneToMany (mappedBy = "votado")
	private Collection <Usuario> votados;
	
	//======================================================

	public Votacion_Participante() {
		super();
		this.votaciones = new ArrayList<>();
		this.votados = new ArrayList<>();
	}

	
	//======================================================

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Collection<Nominacion_Participante> getVotaciones() {
		return votaciones;
	}

	public void setVotaciones(Collection<Nominacion_Participante> votaciones) {
		this.votaciones = votaciones;
	}

	public Collection<Usuario> getVotados() {
		return votados;
	}

	public void setVotados(Collection<Usuario> votados) {
		this.votados = votados;
	}
	
	
}
