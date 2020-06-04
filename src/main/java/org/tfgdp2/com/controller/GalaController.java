package org.tfgdp2.com.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
import org.tfgdp2.com.domain.Nominacion_Juego;
import org.tfgdp2.com.domain.Nominacion_Participante;
import org.tfgdp2.com.domain.Plataforma;
import org.tfgdp2.com.domain.Premio_Juego;
import org.tfgdp2.com.domain.Premio_Participante;
import org.tfgdp2.com.exception.DangerException;
import org.tfgdp2.com.exception.InfoException;
import org.tfgdp2.com.helper.H;
import org.tfgdp2.com.helper.PRG;
import org.tfgdp2.com.repository.GalaRepository;
import org.tfgdp2.com.repository.NominacionJuegoRepository;
import org.tfgdp2.com.repository.NominacionParticipanteRepository;
import org.tfgdp2.com.repository.PremioJuegoRepository;
import org.tfgdp2.com.repository.PremioParticipanteRepository;

@Controller
@RequestMapping(value = "/gala")
public class GalaController {

	@Autowired
	GalaRepository repoGala;
	@Autowired
	PremioJuegoRepository repoPremioJ;
	@Autowired
	PremioParticipanteRepository repoPremioP;
	@Autowired
	NominacionJuegoRepository repoNJuego;
	@Autowired
	NominacionParticipanteRepository repoNParticipante;

	@GetMapping("r")
	public String read(ModelMap m) {
		m.put("galas", repoGala.findAll());
		m.put("view", "gala/r");
		return "_t/frame";
	}
	
	@GetMapping("r2")
	public String read2(ModelMap m) {
		m.put("galas", repoGala.findAll());
		m.put("FechaAc",LocalDate.now());
		m.addAttribute("byCantidadVotosJ", Comparator.comparing(Nominacion_Juego::getCantidadVotos));
		m.addAttribute("byCantidadVotosP", Comparator.comparing(Nominacion_Participante::getCantidadVotos));
		m.put("view", "gala/r2");
		return "_t/frame";
	}

	@GetMapping("c")
	public String cGet(ModelMap m, HttpSession s) throws DangerException {
		H.isRolOK("admin", s);
		m.put("view", "gala/C");
		return "_t/frame";
	}

	@PostMapping("c")
	public void cPost(ModelMap m, @RequestParam("edicion") String edicion,
			@RequestParam("inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
			@RequestParam(value = "premiosP[]") List<String> PremiosP,
			@RequestParam(value = "premiosJ[]") List<String> PremiosJ,
			@RequestParam("fin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin, HttpSession s)
			throws DangerException, InfoException {

		try {

			Gala g = new Gala();
			g.setEdicion(edicion);
			g.setInicio(inicio);
			g.setFin(fin);
			g.setActivo(false);
			repoGala.save(g);
			for (String P : PremiosP) {
				Premio_Participante ppN = new Premio_Participante(
						P + "(" + g.getFin().getMonth() + " " + g.getFin().getYear() + ")");
				ppN.setTiene(g);
				repoPremioP.save(ppN);
				g.getPremiosP().add(ppN);
			}

			for (String J : PremiosJ) {
				Premio_Juego pjN = new Premio_Juego(J + "(" + g.getFin().getMonth() + " " + g.getFin().getYear() + ")");
				pjN.setTiene(g);
				repoPremioJ.save(pjN);
				g.getPremiosJ().add(pjN);
			}

			repoGala.save(g);

		} catch (Exception e) {
			PRG.error("Gala " + edicion + " duplicada" + e.getMessage(), "gala/c");
		}
		PRG.info("Gala " + edicion + " creada correctamente", "gala/r");
	}

	@GetMapping("observacion")
	public String observacion(ModelMap m, @RequestParam("id") Long id, HttpSession s) throws DangerException {
		H.isRolOK("admin", s);
		m.put("gala", repoGala.getOne(id));
		m.put("view", "gala/observacion");
		return "_t/frame";
	}

	@PostMapping("observacion")
	public void anadirObservacion(ModelMap m, @RequestParam("observacion") String observacion,
			@RequestParam("id") Long id, HttpSession s) throws DangerException, InfoException {
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
	public void activar(@RequestParam("id") Long id, HttpSession s) throws DangerException, InfoException {

		try {
			H.isRolOK("admin", s);
			Gala g = repoGala.getOne(id);
			if (g.getActivo()) {
				g.setActivo(false);
			} else {
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
	public void uPost(ModelMap m, @RequestParam("id") Long idGala, @RequestParam("edicion") String edicion,
			@RequestParam("inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
			@RequestParam("fin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin, HttpSession s)
			throws DangerException, InfoException {

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
	public String GalaActual(ModelMap m, HttpSession s) {

		Gala gala = repoGala.findTopByOrderByIdDesc();
		List<Premio_Juego> pjs = (List<Premio_Juego>) gala.getPremiosJ();
		List<Premio_Participante> pps = (List<Premio_Participante>) gala.getPremiosP();
		
		List<Nominacion_Juego> ganadoresJ=new ArrayList<Nominacion_Juego>();
		List<Nominacion_Participante> ganadoresP=new ArrayList<Nominacion_Participante>();
		
		for (Premio_Participante premio_Participante : pps) {
		ganadoresP.add(repoNParticipante.getTopByPremioNombrePremioAndPremioTieneIdOrderByCantidadVotosDesc(premio_Participante.getNombrePremio(), gala.getId()));

		}
		for (Premio_Juego premio_Juego : pjs) {
		ganadoresJ.add(repoNJuego.getTopByPremioNombrePremioAndPremioTieneIdOrderByCantidadVotosDesc(premio_Juego.getNombrePremio(), gala.getId()));

		}
		m.put("ganadoresJ", ganadoresJ);
		m.put("ganadoresP", ganadoresP);
		m.put("fecha", gala.getFin());
		m.put("estado", gala.getActivo());
		m.put("fechaActual", LocalDate.now());
		m.put("fechaDePremio", gala.getFin().plusDays(5));
		

		m.put("view", "gala/galaActiva");
		return "_t/frame";
	}
}
