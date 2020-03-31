package org.tfgdp2.com.domain;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class EntradaForo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int ranking;
	private String comentario;
	private Date fechaPublicacion;
	@ManyToOne
	private Usuario escribe;
    @ManyToOne
    private Foro pertenece;
	
	public EntradaForo() {
		this.ranking=0;
		this.fechaPublicacion=new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public Usuario getEscribe() {
		return escribe;
	}

	public void setEscribe(Usuario escribe) {
		this.escribe = escribe;
	}

	public Foro getPertenece() {
		return pertenece;
	}

	public void setPertenece(Foro pertenece) {
		this.pertenece = pertenece;
	}


	
}
