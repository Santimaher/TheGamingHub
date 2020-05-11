package org.tfgdp2.com.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	@Column(unique = true)
	private String loginname;
	@Column(unique = true)
	private String email;
	private String password;
	private String rol;
	private String img;

//	@ManyToMany(mappedBy="votacion")
//    private Collection<Juego> votaciones;

	@OneToMany(mappedBy = "escribe", fetch = FetchType.EAGER)
	private Collection<EntradaForo> entradas;
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "votado")
	private Collection<Votacion_Foro> votos;
//	@OneToMany(mappedBy="factura")
//    private Collection<Factura> facturas;
//this.votaciones=new ArrayList<>;
//this.entradas=new ArrayList<>;
//this.facturas=new ArrayList<>;
	
	

	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(mappedBy = "votacionesP")
	private Collection<Nominacion_Participante> votadosP;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(mappedBy = "votacionesJ")
	private Collection<Nominacion_Juego> votadosJ;

	// ===============================================
	public Usuario() {
		this.entradas = new HashSet<>();
		this.votadosJ = new HashSet<>();
		this.votadosP = new HashSet<>();

	}

	public Usuario(String nombre, String loginname, String password, String rol) {
		super();
		this.entradas = new HashSet<>();
		this.votadosJ = new HashSet<>();
		this.votadosP = new HashSet<>();
		this.votos = new HashSet<>();
		this.nombre = nombre;
		this.loginname = loginname;
		this.password = password;
		this.rol = rol;
	}

	// ===============================================

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

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = (new BCryptPasswordEncoder()).encode(password);
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public Collection<EntradaForo> getEntradas() {
		return entradas;
	}

	public void setEntradas(Collection<EntradaForo> entradas) {
		this.entradas = entradas;
	}

	public Collection<Nominacion_Participante> getVotadosP() {
		return votadosP;
	}

	public void setVotadosP(Collection<Nominacion_Participante> votadosP) {
		this.votadosP = votadosP;
	}

	public Collection<Nominacion_Juego> getVotadosJ() {
		return votadosJ;
	}

	public void setVotadosJ(Collection<Nominacion_Juego> votadosJ) {
		this.votadosJ = votadosJ;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	public Collection<Votacion_Foro> getVotos() {
		return votos;
	}

	public void setVotos(Collection<Votacion_Foro> votos) {
		this.votos = votos;
	}

//
//	public Collection<Votacion_Participante> getVotadosP() {
//		return votadosP;
//	}
//
//
//
//	public void setVotadosP(Collection<Votacion_Participante> votadosP) {
//		this.votadosP = votadosP;
//	}
//
//
//
//	public Collection<Votacion_Juego> getVotadosJ() {
//		return votadosJ;
//	}
//
//
//
//	public void setVotadosJ(Collection<Votacion_Juego> votadosJ) {
//		this.votadosJ = votadosJ;
//	}

}
