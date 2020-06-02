package org.tfgdp2.com.domain;

import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class EntradaForo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int ranking;
	private String titulo;
	private String comentario;
	@Lob
	private Blob img;
	private LocalDate fechaPublicacion;
	@ManyToOne
	private Usuario escribe;
    @ManyToOne
    private Foro pertenece;
    @OneToMany (mappedBy = "mensajePadre")
    private List<EntradaForo> respuestas;
	@OneToMany(mappedBy = "votante")
	private Collection<Votacion_Foro> votos;
	@ManyToOne 
	private EntradaForo mensajePadre;
    
	
	public EntradaForo(String comentar) {
		this.ranking=0;
		this.comentario=comentar;
		this.fechaPublicacion=LocalDate.now();
		this.votos=new HashSet<>();
	}
	public EntradaForo() {
		this.ranking=0;
		this.fechaPublicacion=LocalDate.now();
	}

	public Collection<Votacion_Foro> getVotos() {
		return votos;
	}
	public void setVotos(Collection<Votacion_Foro> votos) {
		this.votos = votos;
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

	
	public LocalDate getFechaPublicacion() {
		return fechaPublicacion;
	}
	public void setFechaPublicacion(LocalDate fechaPublicacion) {
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

	public List<EntradaForo> getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(List<EntradaForo> respuestas) {
		this.respuestas = respuestas;
	}

	public EntradaForo getMensajePadre() {
		return mensajePadre;
	}

	public void setMensajePadre(EntradaForo mensajePadre) {
		this.mensajePadre = mensajePadre;
	}
	public String getImg() throws SQLException {
		String bytes="";
		if(this.img == null) {
			bytes=null;
		}
		else {
		 bytes = Base64.getEncoder().encodeToString(this.img.getBytes(1l, (int)this.img.length()));
		}
		return bytes;
	}
	public void setImg(Blob img) {
		this.img = img;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	

	
}
