package org.tfgdp2.com.controller;


import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tfgdp2.com.domain.Foro;
import org.tfgdp2.com.domain.Gala;
import org.tfgdp2.com.domain.Plataforma;
import org.tfgdp2.com.domain.Premio_Juego;
import org.tfgdp2.com.domain.Premio_Participante;
import org.tfgdp2.com.exception.DangerException;
import org.tfgdp2.com.exception.InfoException;
import org.tfgdp2.com.helper.H;
import org.tfgdp2.com.helper.PRG;
import org.tfgdp2.com.repository.GalaRepository;
import org.tfgdp2.com.repository.PremioJuegoRepository;
import org.tfgdp2.com.repository.PremioParticipanteRepository;

@Controller
@RequestMapping(value="/gala")
public class GalaController {

	@Autowired GalaRepository repoGala;
	@Autowired PremioJuegoRepository repoPremioJ;
	@Autowired PremioParticipanteRepository repoPremioP;
	
	@GetMapping("r")
	public String read(ModelMap m) {
		m.put("galas", repoGala.findAll());
		m.put("view", "gala/r");
		return "_t/frame";
	}
	
	@GetMapping("c")
	public String cGet(ModelMap m,HttpSession s) throws DangerException {
		H.isRolOK("admin", s);		
		m.put("view", "gala/C");
		return "_t/frame";
	}
	@PostMapping("c")
	public void cPost(ModelMap m,
			@RequestParam("edicion") String edicion,
			@RequestParam("inicio")
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
			@RequestParam("fin")
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin,
			HttpSession s) throws DangerException, InfoException {
		
		try {
			List<Premio_Juego> premiosJ = repoPremioJ.findAll();
			List<Premio_Participante> premiosP = repoPremioP.findAll();
			Gala g = new Gala();
			g.setEdicion(edicion);
			g.setInicio(inicio);
			g.setFin(fin);
			g.setActivo(false);
			for (Premio_Participante Premio_Participante : premiosP) {
				g.getPremiosP().add(Premio_Participante);
				Premio_Participante.getTiene().add(g);
			}
			
			for (Premio_Juego Premio_Juego : premiosJ) {
				g.getPremiosJ().add(Premio_Juego);
				Premio_Juego.getTiene().add(g);
			}
			repoGala.save(g);
			
		} catch (Exception e) {
			PRG.error("Gala " + edicion + " duplicada", "gala/c");
		}
		PRG.info("Gala " + edicion + " creada correctamente", "gala/r");
	}
	@GetMapping("observacion")
	public String observacion(ModelMap m,@RequestParam("id") Long id, HttpSession s) throws DangerException {
		H.isRolOK("admin", s);
		m.put("gala", repoGala.getOne(id));
		m.put("view", "gala/observacion");
		return "_t/frame";
	}
	
	@PostMapping("observacion")
	public void anadirObservacion(ModelMap m,
			@RequestParam("observacion") String observacion,
			@RequestParam("id") Long id,
			HttpSession s) throws DangerException, InfoException {		
		try {
			Gala g = repoGala.getOne(id);
			g.setObservaciones(observacion);
			repoGala.save(g);
			
		} catch (Exception e) {
			PRG.error("Fallo en añadir observacion", "gala/r");
		}
		PRG.info("Observacion añadida", "gala/r");
	}
	
	@PostMapping("activar")
	public void activar(ModelMap m,
			@RequestParam("id") Long id,
			HttpSession s) throws DangerException, InfoException {	
		try {
			H.isRolOK("admin", s);
			Gala g = repoGala.getOne(id);			
			if(g.getActivo()) {
				g.setActivo(false);
			}
			else {
				g.setActivo(true);				
				
			}
			repoGala.save(g);
			
		} catch (Exception e) {
			PRG.error("Fallo en activar/desactivar gala", "gala/r");
		}
		PRG.info("Estado cambiado", "gala/r");
	}
	
	@GetMapping("u")
	public String uGet(@RequestParam("id") Long idGala, ModelMap m, HttpSession s) throws DangerException {
		H.isRolOK("admin", s);
		m.put("gala", repoGala.getOne(idGala));
		m.put("view", "gala/U");
		return "_t/frame";
	}
	
	@PostMapping("u")
	public void uPost(ModelMap m,
			@RequestParam("id") Long idGala,
			@RequestParam("edicion") String edicion,
			@RequestParam("inicio")
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
			@RequestParam("fin")
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin,
			HttpSession s) throws DangerException, InfoException {
		
		try {
			Gala g = repoGala.getOne(idGala);
			g.setEdicion(edicion);
			g.setInicio(inicio);
			g.setFin(fin);
			
			repoGala.save(g);
			
		} catch (Exception e) {
			PRG.error("Gala " + edicion + " duplicada", "gala/r");
		}
		PRG.info("Gala " + edicion + " actualizada correctamente", "gala/r");
	}
	
	@PostMapping("d")
	public void dPost(@RequestParam("id") Long id, ModelMap m, HttpSession s) throws DangerException, InfoException {		
		try {
			H.isRolOK("admin", s);
			repoGala.delete(repoGala.getOne(id));
			
		} catch (Exception e) {
			PRG.error("Error al borrar la Gala", "gala/r");
		}

		PRG.info("Gala borrada correctamente", "gala/r");

	}
	@GetMapping("GA")
	public String GalaActual(ModelMap m, HttpSession s) 
	{
		
		
		int mes=Calendar.MONTH;
		int dia=Calendar.DAY_OF_MONTH;
		int anio=Calendar.YEAR;
		String ca= anio+"/"+mes+"/"+dia;
		List<Gala>galas=repoGala.findByOrderByEdicionDesc();
		m.put("fecha",galas.get(0).getFin());
		m.put("estado",galas.get(0).getActivo());
		m.put("fechaActual",ca);
		m.put("view","gala/galaActiva");
		return "_t/frame";
	}
}
