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
public class Juego {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String Nombre;
	
	private double Precio;
	
	private Integer Stock;
	
	private String Desarrolladora;
	
	private String URL;
	
	private String Img;
	
	@ManyToMany
	private Collection<Plataforma> plataforma;
	//------------------CONSTRUCTOR-------------------------------//

	public Juego() {
		super();
		this.plataforma = new ArrayList<>();
		
	}
	//-----------------------GETTERS Y SETTERS--------------------------//

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

	public double getPrecio() {
		return Precio;
	}

	public void setPrecio(double precio) {
		Precio = precio;
	}

	public Integer getStock() {
		return Stock;
	}

	public void setStock(Integer stock) {
		Stock = stock;
	}

	public String getDesarrolladora() {
		return Desarrolladora;
	}

	public void setDesarrolladora(String desarrolladora) {
		Desarrolladora = desarrolladora;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getImg() {
		return Img;
	}

	public void setImg(String img) {
		Img = img;
	}

	public Collection<Plataforma> getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(Collection<Plataforma> plataforma) {
		this.plataforma = plataforma;
	}
	
	
	
	

}
