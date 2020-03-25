package org.tfgdp2.com.domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String nombre;
	
	@OneToMany(mappedBy = "contiene")
	private Collection<Juego> juegos;

	
	
	//----------Constructor------------//
	public Categoria() {
		super();
		this.juegos = new ArrayList<>();
	}

	
	//---------------GETTERS Y SETTERS-----------------//

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
	
}
