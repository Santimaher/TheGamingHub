package org.tfgdp2.com.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Votacion_Foro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Usuario votante;
	@ManyToOne
	private EntradaForo votado;
	private String voto;
	public Votacion_Foro() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Usuario getVotante() {
		return votante;
	}
	public void setVotante(Usuario votante) {
		this.votante = votante;
	}
	public EntradaForo getVotado() {
		return votado;
	}
	public void setVotado(EntradaForo votado) {
		this.votado = votado;
	}
	public String getVoto() {
		return voto;
	}
	public void setVoto(String voto) {
		this.voto = voto;
	}
	
}
