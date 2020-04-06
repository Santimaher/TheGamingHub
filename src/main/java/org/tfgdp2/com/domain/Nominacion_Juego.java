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
public class Nominacion_Juego {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String nombre;
	
	@ManyToMany(mappedBy="nominado")
	private Collection<Participante> juegos; 
	
	@ManyToOne
	private Premio_Juego premio;
	
	@ManyToOne
	private Votacion_Juego votacion;
	
	//================================================
	
	public Nominacion_Juego() {
		super();
		this.juegos = new ArrayList<>();
	}
	
	public Nominacion_Juego(String nombre) {
		super();
		this.nombre = nombre;
		this.juegos = new ArrayList<>();
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

	public Collection<Participante> getJuegos() {
		return juegos;
	}

	public void setJuegos(Collection<Participante> juegos) {
		this.juegos = juegos;
	}

	public Premio_Juego getPremio() {
		return premio;
	}

	public void setPremio(Premio_Juego premio) {
		this.premio = premio;
	}

	public Votacion_Juego getVotacion() {
		return votacion;
	}

	public void setVotacion(Votacion_Juego votacion) {
		this.votacion = votacion;
	}
	
	
	
}
