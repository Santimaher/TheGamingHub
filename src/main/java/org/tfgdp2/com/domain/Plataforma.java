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
public class Plataforma {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String Nombre;
	
	private String Img;
	
	@ManyToMany
	private Collection<Juego> juego;
	//-----------------------------CONSTRUCTOR---------------------------//

	public Plataforma() {
		super();
		this.juego = new ArrayList<>();
	}
	//------------------------GETTERS Y SETTERS----------------------//

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getImg() {
		return Img;
	}

	public void setImg(String img) {
		Img = img;
	}

	public Collection<Juego> getJuego() {
		return juego;
	}

	public void setJuego(Collection<Juego> juego) {
		this.juego = juego;
	}
	
	
	

}
