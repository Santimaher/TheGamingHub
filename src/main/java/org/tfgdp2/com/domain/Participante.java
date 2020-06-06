package org.tfgdp2.com.domain;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Participante {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;

	private String apellido;

	private String bio;

	private String teaser;

	@Lob
	private Blob img;

	private Boolean estaNominado;

	@ManyToMany
	private Collection<Nominacion_Participante> nominado;

	@ManyToOne
	private Categoria_Participante pertenece;

	// =========================
	public Participante() {
		super();
		this.nominado = new ArrayList<>();
	}

	public Participante(String nombre, String apellido, String bio, String teaser) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.bio = bio;
		this.teaser = teaser;
		this.estaNominado = false;

		this.nominado = new ArrayList<>();

	}

	// ==============================
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getTeaser() {
		return teaser;
	}

	public void setTeaser(String teaser) {
		this.teaser = teaser;
	}

	public String getImg() throws SQLException {
		String bytes = Base64.getEncoder().encodeToString(this.img.getBytes(1l, (int) this.img.length()));
		return bytes;
	}

	public void setImg(Blob img) {
		this.img = img;
	}

	public Boolean getEstaNominado() {
		return estaNominado;
	}

	public void setEstaNominado(Boolean estaNominado) {
		this.estaNominado = estaNominado;
	}

	public Collection<Nominacion_Participante> getNominado() {
		return nominado;
	}

	public void setNominado(Collection<Nominacion_Participante> nominado) {
		this.nominado = nominado;
	}

	public Categoria_Participante getPertenece() {
		return pertenece;
	}

	public void setPertenece(Categoria_Participante pertenece) {
		this.pertenece = pertenece;
	}

}
