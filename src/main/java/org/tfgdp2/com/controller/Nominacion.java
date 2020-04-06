package org.tfgdp2.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tfgdp2.com.domain.Nominacion_Juego;
import org.tfgdp2.com.domain.Nominacion_Participante;
import org.tfgdp2.com.exception.DangerException;
import org.tfgdp2.com.exception.InfoException;
import org.tfgdp2.com.helper.PRG;
import org.tfgdp2.com.repository.NominacionJuegoRepository;
import org.tfgdp2.com.repository.NominacionParticipanteRepository;

@Controller
@RequestMapping(value =  "/nominacion")
public class Nominacion {
	 
	@Autowired
	private NominacionJuegoRepository repoNomJuego;
	
	@Autowired
	private NominacionParticipanteRepository repoNomPar;
	
	@GetMapping("c")
	public String cGet(ModelMap m) {
		try {
			m.put("view","nominacion/c");
		}catch(Exception e) {
			m.put("view", "/");
		}
		return "_t/frame";
	}
	
	@PostMapping("c")
	public void cPost(@RequestParam("nombre") String nombre, @RequestParam("tipo") String tipo) throws DangerException, InfoException{
		try {
			if(tipo=="participante") {
				Nominacion_Participante np = new Nominacion_Participante(nombre);
				repoNomPar.save(np);
			}else {
				Nominacion_Juego nj = new Nominacion_Juego(nombre);
				repoNomJuego.save(nj);
			}
		}catch(Exception e) {
			PRG.error("Error al crear la nominación", "/nominacion/c");
		}
		
		PRG.info("Nominación creada correctamente.","nominacion/r");
	}
	
	@GetMapping("r")
	public String r(ModelMap m) {
		m.put( "nomPartipantes",repoNomPar.findAll());
		m.put("nomJuegos", repoNomJuego.findAll());
		m.put("view", "nominacion/r");
		return "_t/frame";
	}
	
	@PostMapping("dP")
	public String deleteP(@RequestParam("id") Long id) throws DangerException {
		try {
			
			Nominacion_Participante n=repoNomPar.getOne(id);
			repoNomPar.delete(n);
			
		}catch(Exception e) {
			PRG.error("Error al eliminar la nominación","/nominacion/r");
		}
		return "redirect:/nominacion/r";
	}
	
	@PostMapping("dJ")
	public String deleteJ(@RequestParam("id") Long id) throws DangerException {
		try {
			Nominacion_Juego njuego = repoNomJuego.getOne(id);
			repoNomJuego.delete(njuego);
					
		}catch(Exception e) {
			PRG.error("Error al eliminar la nominación","/nominacion/r");
		}
		return "redirect:/nominacion/r";
	}
	
}
