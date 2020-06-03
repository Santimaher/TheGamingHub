package org.tfgdp2.com.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Gala {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String edicion;
	
	private Boolean activo;
	
	private String observaciones;
	@Column(unique = true)
	private LocalDate inicio;
	
	@Column(unique = true)
	private LocalDate fin;
	
	@OneToMany(mappedBy = "tiene")
	private Collection<Premio_Participante> premiosP;
	
	@OneToMany(mappedBy = "tiene")
	private Collection<Premio_Juego> premiosJ;
	
	//------------CONSTRUCTOR----------------
	public Gala() {
		super();
		this.premiosJ = new ArrayList<>();
		this.premiosP = new ArrayList<>();
		
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
	
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	
	
	
	
	
}
