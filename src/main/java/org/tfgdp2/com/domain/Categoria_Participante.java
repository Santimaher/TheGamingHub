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
public class Categoria_Participante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String nombre;

	@OneToMany(mappedBy = "pertenece")
	private Collection<Participante> participantes;

	// ===========================

	public Categoria_Participante() {
		super();
		this.participantes = new ArrayList<Participante>();
	}

	public Categoria_Participante(String nombre) {
		super();
		this.nombre = nombre;
		this.participantes = new ArrayList<Participante>();
	}

	// =====================================
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

	public Collection<Participante> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(Collection<Participante> participantes) {
		this.participantes = participantes;
	}

}
