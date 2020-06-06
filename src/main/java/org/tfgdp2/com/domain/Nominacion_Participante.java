package org.tfgdp2.com.domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Nominacion_Participante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;

	private Integer cantidadVotos;

	@ManyToMany(mappedBy = "nominado")
	private Collection<Participante> participantes;

	@ManyToOne
	private Premio_Participante premio;

	@ManyToMany
	private Collection<Usuario> votacionesP;

	// ================================================

	public Nominacion_Participante() {
		super();
		this.cantidadVotos = 0;
		this.votacionesP = new ArrayList<>();
		this.participantes = new ArrayList<>();
	}

	public Nominacion_Participante(String nombre) {
		super();
		this.nombre = nombre;
		this.cantidadVotos = 0;
		this.votacionesP = new ArrayList<>();
		this.participantes = new ArrayList<>();
	}

	// ===================================================================
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Collection<Participante> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(Collection<Participante> participantes) {
		this.participantes = participantes;
	}

	public Premio_Participante getPremio() {
		return premio;
	}

	public void setPremio(Premio_Participante premio) {
		this.premio = premio;
	}

	public Integer getCantidadVotos() {
		return cantidadVotos;
	}

	public void setCantidadVotos(Integer cantidadVotos) {
		this.cantidadVotos = cantidadVotos;
	}

	public Collection<Usuario> getVotacionesP() {
		return votacionesP;
	}

	public void setVotacionesP(Collection<Usuario> votacionesP) {
		this.votacionesP = votacionesP;
	}

}
