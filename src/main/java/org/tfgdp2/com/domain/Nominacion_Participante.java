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


@Entity
public class Nominacion_Participante {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String nombre;
	
	
	@ManyToMany(mappedBy="nominado")
	private Collection<Participante> participantes;
	
	@ManyToOne
	private Premio_Participante premio;
	
	@ManyToOne
	
	private Nominacion_Participante votacion;
	
	//================================================
	

	public Nominacion_Participante() {
		super();
		this.participantes = new ArrayList<>();
	}
	
	public Nominacion_Participante(String nombre, Participante idParticipante) {
		super();
		this.nombre = nombre;
		this.participantes = new ArrayList<>();
	}

	

	
	//===================================================================
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
	

	public Nominacion_Participante getVotacion() {
		return votacion;
	}

	public void setVotacion(Nominacion_Participante votacion) {
		this.votacion = votacion;
	}
	
	
}
