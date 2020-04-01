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
public class Nominacion_Juego {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String nombre;
	
	private Juego idJuego;
	
	@ManyToMany(mappedBy="nominado")
	private Collection<Participante> juegos; 
	
	//================================================
	
	public Nominacion_Juego() {
		super();
		this.juegos = new ArrayList<>();
	}
	
	public Nominacion_Juego(String nombre, Juego idJuego) {
		super();
		this.nombre = nombre;
		this.idJuego = idJuego;
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

	public Juego getIdJuego() {
		return idJuego;
	}

	public void setIdJuego(Juego idJuego) {
		this.idJuego = idJuego;
	}

	public Collection<Participante> getJuegos() {
		return juegos;
	}

	public void setJuegos(Collection<Participante> juegos) {
		this.juegos = juegos;
	}
	
	
	
}
