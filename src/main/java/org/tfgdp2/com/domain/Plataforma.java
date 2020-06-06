package org.tfgdp2.com.domain;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

@Entity
public class Plataforma {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String nombre;
	
	private String familia;
	
	@Lob
	private Blob img;
	
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

	public String getImg() throws SQLException {
		String bytes = Base64.getEncoder().encodeToString(this.img.getBytes(1l, (int)this.img.length()));
		return bytes;
	}

	public void setImg(Blob img) {
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
