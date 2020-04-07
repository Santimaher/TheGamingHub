package org.tfgdp2.com.domain;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	@Column(unique=true)
	private String loginname;
	@Column(unique=true)
	private String email;
	private String password;
	private String rol;

//	@ManyToMany(mappedBy="votacion")
//    private Collection<Juego> votaciones;
	@OneToMany(mappedBy="escribe",fetch= FetchType.EAGER)
    private Collection<EntradaForo> entradas;
//	@OneToMany(mappedBy="factura")
//    private Collection<Factura> facturas;
//this.votaciones=new ArrayList<>;
//this.entradas=new ArrayList<>;
//this.facturas=new ArrayList<>;
	
	@ManyToOne
	private Votacion_Participante votado;
	
	@ManyToOne
	private Votacion_Juego juegovotado;
	
	//===============================================
	public Usuario() {
		
	}
	
	
	
	public Usuario(String nombre, String loginname, String password, String rol) {
		super();
		this.nombre = nombre;
		this.loginname = loginname;
		this.password = password;
		this.rol = rol;
	}



	//===============================================
	
	
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

	public Votacion_Participante getVotado() {
		return votado;
	}

	public void setVotado(Votacion_Participante votado) {
		this.votado = votado;
	}

	public Votacion_Juego getJuegovotado() {
		return juegovotado;
	}

	public void setJuegovotado(Votacion_Juego juegovotado) {
		this.juegovotado = juegovotado;
	}

}
