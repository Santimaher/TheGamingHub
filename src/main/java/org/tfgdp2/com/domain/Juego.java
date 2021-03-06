package org.tfgdp2.com.domain;

import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Juego {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;

	private LocalDate fechaLanzamiento;

	private String desarrolladora;

	@Lob
	private Blob img;

	private String imgPre;

	private String teaser;

	private Boolean estaNominado;

	@ManyToMany
	private Collection<Plataforma> plataformas;

	@ManyToMany
	private Collection<Categoria_Juego> pertenece;

	@ManyToMany
	private Collection<Nominacion_Juego> nominado;

	@OneToMany(mappedBy = "juego")
	private Collection<Foro> foro;

	// ------------------CONSTRUCTOR-------------------------------//

	public Juego() {
		super();
		this.plataformas = new ArrayList<>();
		this.foro = new ArrayList<>();
		this.nominado = new ArrayList<>();
		this.pertenece = new ArrayList<>();
		this.estaNominado = false;

	}
	// -----------------------GETTERS Y SETTERS--------------------------//

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

	public String getDesarrolladora() {
		return desarrolladora;
	}

	public void setDesarrolladora(String desarrolladora) {
		this.desarrolladora = desarrolladora;
	}

	public Boolean getEstaNominado() {
		return estaNominado;
	}

	public void setEstaNominado(Boolean estaNominado) {
		this.estaNominado = estaNominado;
	}

	public Collection<Plataforma> getPlataformas() {
		return plataformas;
	}

	public void setPlataformas(Collection<Plataforma> plataforma) {
		this.plataformas = plataforma;
	}

	public String getTeaser() {
		return teaser;
	}

	public void setTeaser(String teaser) {
		this.teaser = teaser;
	}

	public LocalDate getFechaLanzamiento() {
		return fechaLanzamiento;
	}

	public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
		this.fechaLanzamiento = fechaLanzamiento;
	}

	public Collection<Foro> getForo() {
		return foro;
	}

	public void setForo(Collection<Foro> foro) {
		this.foro = foro;
	}

	public Collection<Categoria_Juego> getPertenece() {
		return pertenece;
	}

	public void setPertenece(Collection<Categoria_Juego> pertenece) {
		this.pertenece = pertenece;
	}

	public Collection<Nominacion_Juego> getNominado() {
		return nominado;
	}

	public void setNominado(Collection<Nominacion_Juego> nominado) {
		this.nominado = nominado;
	}

	public String getImg() throws SQLException {
		String bytes = Base64.getEncoder().encodeToString(this.img.getBytes(1l, (int) this.img.length()));
		return bytes;

	}

	public void setImg(Blob img) {
		this.img = img;
	}

	public String getImgPre() {
		return imgPre;
	}

	public void setImgPre(String imgPre) {
		this.imgPre = imgPre;
	}

}
