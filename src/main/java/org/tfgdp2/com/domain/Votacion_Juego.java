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
//public class Votacion_Juego {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//
//	@ManyToOne
//	private Nominacion_Juego votacionJ;
//
//	@ManyToOne(fetch = FetchType.EAGER)
//	private Usuario votadoJ;
//
//	// ==============================
//	public Votacion_Juego() {
//		super();
//	}
//	
//	
//	public Votacion_Juego(Nominacion_Juego votacionJ, Usuario votadoJ) {
//		super();
//		this.votacionJ = votacionJ;
//		this.votadoJ = votadoJ;
//	}
//
//
//	// ========================
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public Usuario getVotadoJ() {
//		return votadoJ;
//	}
//
//	public void setvotadoJ(Usuario votadoJ) {
//		this.votadoJ = votadoJ;
//	}
//
//	public Nominacion_Juego getVotacionJ() {
//		return votacionJ;
//	}
//
//	public void setVotacionJ(Nominacion_Juego votacionJ) {
//		this.votacionJ = votacionJ;
//	}
//
//}
