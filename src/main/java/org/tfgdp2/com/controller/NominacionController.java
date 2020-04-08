package org.tfgdp2.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tfgdp2.com.domain.Juego;
import org.tfgdp2.com.domain.Participante;
import org.tfgdp2.com.exception.DangerException;
import org.tfgdp2.com.exception.InfoException;
import org.tfgdp2.com.helper.PRG;
import org.tfgdp2.com.repository.JuegoRepository;
import org.tfgdp2.com.repository.ParticipanteRepository;

@Controller
@RequestMapping(value =  "/nominacion")
public class NominacionController {
	
	@Autowired
	private ParticipanteRepository repoParticipante;
	
	@Autowired
	private JuegoRepository repoJuego;
	
	
	@GetMapping("nominarParticipante")
	public String nominarParticipante(ModelMap m) {
		m.put("participantes", repoParticipante.findAll());
		m.put("view", "nominacion/nominarParticipante");
		return "_t/frame";
	}
	
	@PostMapping("nominarParticipante")
	public void nominarPpost(@RequestParam("id") Long idParticipante) throws InfoException, DangerException {
		Participante p = repoParticipante.getOne(idParticipante);
		if(p.getEstaNominado()==false) {
			p.setEstaNominado(true);
			//PRG.info("Participante nominado correctamente.");
		} else {
			PRG.error("El partipante no se pudo nominar");
		}
	}
	
	@GetMapping("nominarJuego")
	public String nominarJuego(ModelMap m) {
		m.put("juegos",repoJuego.findAll());
		m.put("view", "nominacion/nominarJuego");
		return ("_t/frame");
	}
	
	@PostMapping("nominarJuego")
	public void nominarJpost(@RequestParam("id") Long idJuego) throws DangerException, InfoException{
		Juego j = repoJuego.getOne(idJuego);
		if(j.getEstaNominado()==false) {
			j.setEstaNominado(true);
			PRG.info("El juego nominado correctamente.", "nominacion/nominarParticipante");
		} else {
			PRG.error("El juego no se pudo nominar");
		}
      }
}
