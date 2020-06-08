package org.tfgdp2.com.domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Foro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// tipos de foro: Fan Art, Debug, Memes, Misc
	private String tipo;

	@OneToMany(mappedBy = "pertenece")
	private Collection<EntradaForo> pertenecen;

	@ManyToOne
	private Juego juego;

	// --------------Constructor-----------------------
	public Foro() {
		this.pertenecen = new ArrayList<>();
	}

	public Foro(String tipo) {
		super();
		this.tipo = tipo;
		this.pertenecen = new ArrayList<>();
	}

	// -------------GETTERS Y SETTERS----------------
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Collection<EntradaForo> getPertenecen() {
		return pertenecen;
	}

	public void setPertenecen(Collection<EntradaForo> pertenecen) {
		this.pertenecen = pertenecen;
	}

	public Juego getJuego() {
		return juego;
	}

	public void setJuego(Juego juego) {
		this.juego = juego;
	}

}
