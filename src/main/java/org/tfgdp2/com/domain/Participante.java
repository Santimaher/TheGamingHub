package org.tfgdp2.com.domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Participante {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	
	private String apellido;
	
	private String bio;
	
	private String teaser;
	
	private String img;
	
	@ManyToOne
	private Nominacion nominado;
	
	@ManyToMany
	private Collection <Nominacion_Juego> nominados;

	@ManyToOne
	private Categoria_Participante participante;
	
	//=========================
	public Participante() {
		super();
		this.nominados = new ArrayList<>();
	}
	
	public Participante(String nombre, String apellido, String bio, String teaser) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.bio = bio;
		this.teaser = teaser;
		this.nominados = new ArrayList<>();
		
	}

	//==============================
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getTeaser() {
		return teaser;
	}

	public void setTeaser(String teaser) {
		this.teaser = teaser;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Nominacion getNominado() {
		return nominado;
	}

	public void setNominado(Nominacion nominado) {
		this.nominado = nominado;
	}
	
	public Categoria_Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Categoria_Participante participante) {
		this.participante = participante;
	}

	public Collection<Nominacion_Juego> getNominados() {
		return nominados;
	}

	public void setNominados(Collection<Nominacion_Juego> nominados) {
		this.nominados = nominados;
	}


	
}
