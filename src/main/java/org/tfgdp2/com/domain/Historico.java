package org.tfgdp2.com.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Historico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String observaciones;

	@ManyToOne
	private Gala pertenece;

	// -------------CONSTRUCTOR--------------
	public Historico(Long id, String observaciones, Gala pertenece) {
		super();

	}

	// -------------GETTERS Y SETTERS-------------

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Gala getPertenece() {
		return pertenece;
	}

	public void setPertenece(Gala pertenece) {
		this.pertenece = pertenece;
	}
	
	
}
