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
public class Nominacion_Juego {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String nombre;
	
	private Integer cantidadVotos;
	
	@ManyToMany(mappedBy="nominado")
	private Collection<Juego> juegos; 
	
	@ManyToOne
	private Premio_Juego premio;
	
	@ManyToMany
	private Collection<Usuario> votacionesJ;
	
	//================================================
	
	public Nominacion_Juego() {
		super();
		this.votacionesJ=new ArrayList<>();
		this.cantidadVotos=0;
		this.juegos = new ArrayList<>();
	}
	
	public Nominacion_Juego(String nombre) {
		super();
		this.nombre = nombre;
		this.cantidadVotos=0;
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

	public Collection<Juego> getJuegos() {
		return juegos;
	}

	public void setJuegos(Collection<Juego> juegos) {
		this.juegos = juegos;
	}

	public Premio_Juego getPremio() {
		return premio;
	}

	public void setPremio(Premio_Juego premio) {
		this.premio = premio;
	}

	
	public Integer getCantidadVotos() {
		return cantidadVotos;
	}

	public void setCantidadVotos(Integer cantidadVotos) {
		this.cantidadVotos = cantidadVotos;
	}

	public Collection<Usuario> getVotacionesJ() {
		return votacionesJ;
	}

	public void setVotacionesJ(Collection<Usuario> votacionesJ) {
		this.votacionesJ = votacionesJ;
	}

//	public Collection<Votacion_Juego> getVotacionesJ() {
//		return votacionesJ;
//	}
//
//	public void setVotacionesJ(Collection<Votacion_Juego> votacionesJ) {
//		this.votacionesJ = votacionesJ;
//	}
	
	
	
}
