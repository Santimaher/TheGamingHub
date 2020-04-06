package org.tfgdp2.com.domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Votacion_Juego {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToMany(mappedBy= "votacion")
	private Collection<Nominacion_Juego> votaciones;
	
	@OneToMany(mappedBy = "juegovotado")
	private Collection<Usuario> juegosvotados;
	
	//==============================
	public Votacion_Juego() {
		super();
		this.votaciones = new ArrayList<>();
		this.juegosvotados = new ArrayList<>();
	}
	//========================

	public Collection<Nominacion_Juego> getVotaciones() {
		return votaciones;
	}

	public void setVotaciones(Collection<Nominacion_Juego> votaciones) {
		this.votaciones = votaciones;
	}

	public Collection<Usuario> getJuegosvotados() {
		return juegosvotados;
	}

	public void setJuegosvotados(Collection<Usuario> juegosvotados) {
		this.juegosvotados = juegosvotados;
	}
	
	
}
