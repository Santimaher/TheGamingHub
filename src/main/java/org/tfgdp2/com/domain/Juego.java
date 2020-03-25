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
public class Juego {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String nombre;
	
	private double precio;
	
	private Integer stock;
	
	private String desarrolladora;
	
	private String url;
	
	private String img;
	
	@ManyToMany(mappedBy = "juego")
	private Collection<Plataforma> plataforma;
	
	@ManyToOne
	private Categoria contiene;
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
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getDesarrolladora() {
		return desarrolladora;
	}

	public void setDesarrolladora(String desarrolladora) {
		this.desarrolladora = desarrolladora;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Collection<Plataforma> getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(Collection<Plataforma> plataforma) {
		this.plataforma = plataforma;
	}

	public Categoria getContiene() {
		return contiene;
	}

	public void setContiene(Categoria contiene) {
		this.contiene = contiene;
	}

	
	
	
	

}
