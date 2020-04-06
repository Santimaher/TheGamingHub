package org.tfgdp2.com.controller;

import java.time.LocalDate;
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
import org.tfgdp2.com.domain.Gala;
import org.tfgdp2.com.domain.Plataforma;
import org.tfgdp2.com.exception.DangerException;
import org.tfgdp2.com.exception.InfoException;
import org.tfgdp2.com.helper.H;
import org.tfgdp2.com.helper.PRG;
import org.tfgdp2.com.repository.GalaRepository;

@Controller
@RequestMapping(value="/gala")
public class GalaController {

	@Autowired GalaRepository repoGala;
	
	@GetMapping("r")
	public String read(ModelMap m) {
		m.put("galas", repoGala.findAll());
		m.put("view", "gala/r");
		return "/_t/frame";
	}
	
	@GetMapping("c")
	public String cGet(ModelMap m,HttpSession s) throws DangerException {
		//H.isRolOK("admin", s);		
		m.put("view", "/gala/c");
		return "/_t/frame";
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
			Gala g = new Gala();
			g.setEdicion(edicion);
			g.setInicio(inicio);
			g.setFin(fin);
			g.setActivo(false);
			repoGala.save(g);
			
		} catch (Exception e) {
			PRG.error("Gala " + edicion + " duplicada", "/gala/c");
		}
		PRG.info("Gala " + edicion + " creada correctamente", "/gala/r");
	}
	@GetMapping("observacion")
	public String observacion(ModelMap m,@RequestParam("id") Long id) {
		m.put("gala", repoGala.getOne(id));
		m.put("view", "/gala/observacion");
		return "/_t/frame";
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
			PRG.error("Fallo en añadir observacion", "/gala/r");
		}
		PRG.info("Observacion añadida", "/gala/r");
	}
	
	@PostMapping("activar")
	public void activar(ModelMap m,
			@RequestParam("id") Long id,
			HttpSession s) throws DangerException, InfoException {		
		try {
			Gala g = repoGala.getOne(id);
			Boolean estado = g.isActivo();
			if(estado=true) {
				g.setActivo(false);
				
			}
			else {
				g.setActivo(true);
				
			}
			repoGala.save(g);
			
		} catch (Exception e) {
			PRG.error("Fallo en activar/desactivar gala", "/gala/r");
		}
		PRG.info("Estado cambiado", "/gala/r");
	}
}
