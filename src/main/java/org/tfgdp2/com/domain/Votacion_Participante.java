//package org.tfgdp2.com.domain;
//
//import java.util.ArrayList;
//import java.util.Collection;
//
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//
//@Entity
//public class Votacion_Participante {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//	
//	@ManyToOne
//	private Nominacion_Participante votacionP;
//	
//	@ManyToOne(fetch = FetchType.EAGER)
//	private Usuario votadoP;
//	
//	//======================================================
//
//	public Votacion_Participante() {
//		super();
//		
//	}
//		
//	public Votacion_Participante(Nominacion_Participante votacionP, Usuario votadoP) {
//		super();
//		this.votacionP = votacionP;
//		this.votadoP = votadoP;
//	}
//
//
//
//
//	//======================================================
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public Usuario getVotadoP() {
//		return votadoP;
//	}
//
//
//	public void setVotadoP(Usuario votadoP) {
//		this.votadoP = votadoP;
//	}
//
//
//	public Nominacion_Participante getVotacionP() {
//		return votacionP;
//	}
//
//
//	public void setVotacionP(Nominacion_Participante votacionP) {
//		this.votacionP = votacionP;
//	}
//	
//
//	
//
//	
//	
//	
//}
