package org.tfgdp2.com.domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class Nominacion_Participante {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String nombre;
	
	private Participante idParticipante;
	
	@ManyToMany(mappedBy="nominado")
	private Collection<Participante> participantes;
	
	//================================================
	
	public Nominacion_Participante() {
		super();
		this.participantes = new ArrayList<>();
	}
	
	public Nominacion_Participante(String nombre, Participante idParticipante) {
		super();
		this.nombre = nombre;
		this.idParticipante = idParticipante;
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

	public Participante getIdParticipante() {
		return idParticipante;
	}

	public void setIdParticipante(Participante idParticipante) {
		this.idParticipante = idParticipante;
	}

	public Collection<Participante> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(Collection<Participante> participantes) {
		this.participantes = participantes;
	}
	
	
	
	
}
