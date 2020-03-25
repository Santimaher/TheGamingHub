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

public class Nominacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String nombre;
	
	@OneToMany(mappedBy = "nominado")
	private Collection <Participante> nominados;
	
	//================
	
	public Nominacion(String nombre) {
		super();
		this.nombre = nombre;
		this.nominados = new ArrayList<Participante>();
	}
	public Nominacion() {
		super();
		this.nominados = new ArrayList<Participante>();
	}
	
	//================
	
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
	public Collection<Participante> getNominados() {
		return nominados;
	}
	public void setNominados(Collection<Participante> nominados) {
		this.nominados = nominados;
	}
	
	
	
}
