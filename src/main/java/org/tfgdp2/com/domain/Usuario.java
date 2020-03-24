package org.tfgdp2.com.domain;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
//	@OneToMany(mappedBy="entradaForo")
//    private Collection<EntradaForo> entradas;
//	@OneToMany(mappedBy="factura")
//    private Collection<Factura> facturas;
//this.votaciones=new ArrayList<>;
//this.entradas=new ArrayList<>;
//this.facturas=new ArrayList<>;
	public Usuario() {
	
	}
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
}
