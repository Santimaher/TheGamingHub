package org.tfgdp2.com.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Gala {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String edicion;
	
	@Column(unique = true)
	private LocalDate inicio;
	
	@Column(unique = true)
	private LocalDate fin;
	
	@OneToMany(mappedBy = "tiene")
	private Collection<Premio_Participante> premiosP;
	
	@OneToMany(mappedBy = "tiene")
	private Collection<Premio_Juego> premiosJ;
	
	@OneToMany(mappedBy = "pertenece")
	private Collection<Historico> pertenece;
	//------------CONSTRUCTOR----------------
	public Gala() {
		super();
		this.premiosJ = new ArrayList<>();
		this.premiosP = new ArrayList<>();
		this.pertenece = new ArrayList<>();
	}
	//---------------GETTERS Y SETTERS-------------------
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEdicion() {
		return edicion;
	}
	public void setEdicion(String edicion) {
		this.edicion = edicion;
	}
	public Collection<Premio_Participante> getPremiosP() {
		return premiosP;
	}
	public void setPremiosP(Collection<Premio_Participante> premiosP) {
		this.premiosP = premiosP;
	}
	public Collection<Premio_Juego> getPremiosJ() {
		return premiosJ;
	}
	public void setPremiosJ(Collection<Premio_Juego> premiosJ) {
		this.premiosJ = premiosJ;
	}
	public Collection<Historico> getPertenece() {
		return pertenece;
	}
	public void setPertenece(Collection<Historico> pertenece) {
		this.pertenece = pertenece;
	}
	public LocalDate getInicio() {
		return inicio;
	}
	public void setInicio(LocalDate inicio) {
		this.inicio = inicio;
	}
	public LocalDate getFin() {
		return fin;
	}
	public void setFin(LocalDate fin) {
		this.fin = fin;
	}
	
	
	
	
	
}
