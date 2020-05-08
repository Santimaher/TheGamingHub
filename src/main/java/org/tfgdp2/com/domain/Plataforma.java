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
	private String nombre;
	
	private String familia;
	
	private String img;
	
	@ManyToMany(mappedBy = "plataformas")
	private Collection<Juego> juegos;
	//-----------------------------CONSTRUCTOR---------------------------//

	public Plataforma() {
		super();
		this.juegos = new ArrayList<>();
	}
	//------------------------GETTERS Y SETTERS----------------------//

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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Collection<Juego> getJuegos() {
		return juegos;
	}

	public void setJuegos(Collection<Juego> juego) {
		this.juegos = juego;
	}

	public String getFamilia() {
		return familia;
	}

	public void setFamilia(String familia) {
		this.familia = familia;
	}

	
	
	
	

}
