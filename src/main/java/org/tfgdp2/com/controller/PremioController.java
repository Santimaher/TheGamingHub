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
import org.tfgdp2.com.domain.Participante;
import org.tfgdp2.com.domain.Premio_Juego;
import org.tfgdp2.com.domain.Premio_Participante;
import org.tfgdp2.com.exception.DangerException;
import org.tfgdp2.com.exception.InfoException;
import org.tfgdp2.com.helper.PRG;
import org.tfgdp2.com.repository.JuegoRepository;
import org.tfgdp2.com.repository.NominacionJuegoRepository;
import org.tfgdp2.com.repository.NominacionParticipanteRepository;
import org.tfgdp2.com.repository.ParticipanteRepository;
import org.tfgdp2.com.repository.PremioJuegoRepository;
import org.tfgdp2.com.repository.PremioParticipanteRepository;

@Controller
@RequestMapping("/premio")
public class PremioController {

	@Autowired
	private NominacionJuegoRepository repoNomJuego;

	@Autowired
	private NominacionParticipanteRepository repoNomPar;

	@Autowired
	private PremioParticipanteRepository repoPremioPar;

	@Autowired
	private PremioJuegoRepository repoPremioJuego;

	@Autowired
	private ParticipanteRepository repoParticipante;

	@Autowired
	private JuegoRepository repoJuego;

	@GetMapping("c")
	public String cGet(ModelMap m) {
		try {
			m.put("view", "premio/c");
		} catch (Exception e) {
			m.put("view", "home");
		}
		return "_t/frame";
	}

	@PostMapping("c")
	public void cPost(@RequestParam("nombre") String nombre, @RequestParam("tipo") String tipo)
			throws DangerException, InfoException {
		
			if (tipo.equals("participante")) {
				try {
				Premio_Participante pp = new Premio_Participante(nombre);
				repoPremioPar.save(pp);
				}
				catch (Exception e) {
					PRG.error("Error al crear el premio. " + e.getMessage() + "", "/premio/c");
				}
			}
			if (tipo.equals("juego")) {
				try {
				Premio_Juego pj = new Premio_Juego(nombre);
				repoPremioJuego.save(pj);
				}
				catch (Exception e) {
					PRG.error("Error al crear el premio. " + e.getMessage() + "", "/premio/c");
				}
			}
		 
		PRG.info("Premio creado", "/premio/r");
	}

	@GetMapping("r")
	public String r(ModelMap m) {

		m.put("nomPartipantes", repoPremioPar.findAll());
		m.put("nomJuegos", repoPremioJuego.findAll());
		m.put("view", "premio/r");

		return "_t/frame";
	}

	@PostMapping("dP")
	public String deleteP(@RequestParam("id") Long id) throws DangerException {
		try {

			Premio_Participante n = repoPremioPar.getOne(id);
			repoPremioPar.delete(n);

		} catch (Exception e) {
			PRG.error("Error al eliminar el premio", "/premio/r");
		}
		return "redirect:/premio/r";
	}

	@PostMapping("dJ")
	public String deleteJ(@RequestParam("id") Long id) throws DangerException {
		try {
			Premio_Juego njuego = repoPremioJuego.getOne(id);
			repoPremioJuego.delete(njuego);

		} catch (Exception e) {
			PRG.error("Error al eliminar el premio", "/premio/r");
		}
		return "redirect:/premio/r";
	}

	@GetMapping("addVotoP")
	public String addVotoP(ModelMap m, @RequestParam("id") Long id) {
		m.put("premio", repoPremioPar.getOne(id));
		// m.put("participantes",repoParticipante.findByIsNominadoTrue());
		m.put("view", "premmio/addVotoP");
		return "_t/frame";
	}

	@PostMapping("addVotoP")
	public void addVotoPost(@RequestParam("id") Long idParticipante, @RequestParam("idPremio") Long idPremio) {
		Participante parti = repoParticipante.getOne(idParticipante);
		Premio_Participante premioP = repoPremioPar.getOne(idPremio);
		try {
			Integer aux = parti.getCantidadVotos();
			aux = +1;
			parti.setCantidadVotos(aux);
			Nominacion_Participante nompar = new Nominacion_Participante();
			nompar.getParticipantes().add(parti);
		} catch (Exception e) {

		}
	}
}
