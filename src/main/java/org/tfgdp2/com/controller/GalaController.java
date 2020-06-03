package org.tfgdp2.com.controller;


import java.time.LocalDate;
import java.util.Calendar;
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
@RequestMapping(value="/gala")
public class GalaController {

	@Autowired GalaRepository repoGala;
	@Autowired PremioJuegoRepository repoPremioJ;
	@Autowired PremioParticipanteRepository repoPremioP;
	@Autowired NominacionJuegoRepository repoNJuego;
	@Autowired NominacionParticipanteRepository repoNParticipante;
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
		m.put("premiosJ",repoPremioJ.findAll());
		m.put("premiosP",repoPremioP.findAll());
		return "_t/frame";
	}
	@PostMapping("c")
	public void cPost(ModelMap m,
			@RequestParam("edicion") String edicion,
			@RequestParam("inicio")
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
			@RequestParam(value = "premiosP[]") List<String> PremiosP,
			@RequestParam(value = "premiosJ[]") List<String> PremiosJ,
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
			 for (String P : PremiosP) {
			 Premio_Participante ppN=new Premio_Participante(P+")");
			 ppN.setTiene(g);
			 repoPremioP.save(ppN);
			 g.getPremiosP().add(ppN);  }
			  
			 for (String J : PremiosJ) {
			 Premio_Juego pjN=new Premio_Juego(J+"()");
			 pjN.setTiene(g);
			 repoPremioJ.save(pjN);
			 g.getPremiosJ().add(pjN);  }
			 
			repoGala.save(g);
			
		} catch (Exception e) {
			PRG.error("Gala " + edicion + " duplicada"+e.getMessage(), "gala/c");
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
	public void activar(@RequestParam("id") Long id,HttpSession s) throws DangerException, InfoException {	
		
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
		List<Gala>galas=repoGala.findByOrderByEdicionDesc();
		m.put("fecha",galas.get(0).getFin());
		m.put("estado",galas.get(0).getActivo());
		m.put("fechaActual",LocalDate.now());
		m.put("fechaDePremio",galas.get(0).getFin().plusDays(5));
		m.put("JuegoDeMayorImpacto",repoNJuego.getTopByPremioNombrePremioAndPremioTieneIdOrderByCantidadVotosDesc("Juego de Mayor Impacto", galas.get(0).getId()));
		m.put("JuegoDelAnio",repoNJuego.getTopByPremioNombrePremioAndPremioTieneIdOrderByCantidadVotosDesc("Juego del Año", galas.get(0).getId()));
		m.put("MejorBandaSonora",repoNJuego.getTopByPremioNombrePremioAndPremioTieneIdOrderByCantidadVotosDesc("Mejor Banda Sonora", galas.get(0).getId()));
		m.put("MejorEventoDeEsport",repoNJuego.getTopByPremioNombrePremioAndPremioTieneIdOrderByCantidadVotosDesc("Mejor Evento de eSports", galas.get(0).getId()));
		m.put("MejorJuegoComoServicio",repoNJuego.getTopByPremioNombrePremioAndPremioTieneIdOrderByCantidadVotosDesc("Mejor Juego como Servicio", galas.get(0).getId()));
		m.put("MejorJuegoDeAccion",repoNJuego.getTopByPremioNombrePremioAndPremioTieneIdOrderByCantidadVotosDesc("Mejor Juego de Accion", galas.get(0).getId()));
		m.put("MejorJuegoDeCarreras",repoNJuego.getTopByPremioNombrePremioAndPremioTieneIdOrderByCantidadVotosDesc("Mejor Juego de Deportes/Carreras", galas.get(0).getId()));
		m.put("MejorJuegoDeEsports",repoNJuego.getTopByPremioNombrePremioAndPremioTieneIdOrderByCantidadVotosDesc("Mejor Juego de eSports", galas.get(0).getId()));
		m.put("MejorJuegoDeEstrategia",repoNJuego.getTopByPremioNombrePremioAndPremioTieneIdOrderByCantidadVotosDesc("Mejor Juego de Estrategia", galas.get(0).getId()));
		m.put("MejorJuegoDeLucha",repoNJuego.getTopByPremioNombrePremioAndPremioTieneIdOrderByCantidadVotosDesc("Mejor Juego de Lucha ", galas.get(0).getId()));
        m.put("MejorJuegoDeRol",repoNJuego.getTopByPremioNombrePremioAndPremioTieneIdOrderByCantidadVotosDesc("Mejor Juego de rol" , galas.get(0).getId()));
		m.put("MejorJuegoFamiliar",repoNJuego.getTopByPremioNombrePremioAndPremioTieneIdOrderByCantidadVotosDesc("Mejor Juego Familiar", galas.get(0).getId()));
		m.put("MejorJuegoIndie",repoNJuego.getTopByPremioNombrePremioAndPremioTieneIdOrderByCantidadVotosDesc("Mejor Juego Indie", galas.get(0).getId()));
		m.put("MejorJuegoMultijugador",repoNJuego.getTopByPremioNombrePremioAndPremioTieneIdOrderByCantidadVotosDesc("Mejor Juego Multijugador", galas.get(0).getId()));
		m.put("MejorMoviles",repoNJuego.getTopByPremioNombrePremioAndPremioTieneIdOrderByCantidadVotosDesc("Mejor Juego para Moviles", galas.get(0).getId()));
		m.put("MejorJuegoVR",repoNJuego.getTopByPremioNombrePremioAndPremioTieneIdOrderByCantidadVotosDesc("Mejor Juego VR", galas.get(0).getId()));
		m.put("MejorNarrativa",repoNJuego.getTopByPremioNombrePremioAndPremioTieneIdOrderByCantidadVotosDesc("Mejor Narrativa", galas.get(0).getId()));
		m.put("MejorComunidad",repoNJuego.getTopByPremioNombrePremioAndPremioTieneIdOrderByCantidadVotosDesc("Mejor Soporte a la Comunidad", galas.get(0).getId()));
		
		m.put("MejorActor",repoNParticipante.getTopByPremioNombrePremioAndPremioTieneIdOrderByCantidadVotosDesc("Mejor actor", galas.get(0).getId()));
		m.put("MejorActorDeDoblaje",repoNParticipante.getTopByPremioNombrePremioAndPremioTieneIdOrderByCantidadVotosDesc("Mejor actor de doblaje", galas.get(0).getId()));
		m.put("MejorAnfitrionEsports",repoNParticipante.getTopByPremioNombrePremioAndPremioTieneIdOrderByCantidadVotosDesc("Mejor anfitrion de eSports", galas.get(0).getId()));
		m.put("MejorCreadorDeContenido",repoNParticipante.getTopByPremioNombrePremioAndPremioTieneIdOrderByCantidadVotosDesc("Mejor creador de contenido", galas.get(0).getId()));
		m.put("MejorDesarrolladora",repoNParticipante.getTopByPremioNombrePremioAndPremioTieneIdOrderByCantidadVotosDesc("Mejor desarrolladora", galas.get(0).getId()));
		m.put("MejorDireccion",repoNParticipante.getTopByPremioNombrePremioAndPremioTieneIdOrderByCantidadVotosDesc("Mejor direccion", galas.get(0).getId()));
//		m.put("MejorDireccionArtistica",repoNParticipante.getTopByPremioNombrePremioAndPremioTieneIdOrderByCantidadVotosDesc("Mejor dirección Artistica", galas.get(0).getId()));
		m.put("MejorEntrenador",repoNParticipante.getTopByPremioNombrePremioAndPremioTieneIdOrderByCantidadVotosDesc("Mejor entrenador de eSports", galas.get(0).getId()));
		m.put("MejorEquipo",repoNParticipante.getTopByPremioNombrePremioAndPremioTieneIdOrderByCantidadVotosDesc("Mejor equipo de eSports", galas.get(0).getId()));
		m.put("MejorGuionista",repoNParticipante.getTopByPremioNombrePremioAndPremioTieneIdOrderByCantidadVotosDesc("Mejor guionista", galas.get(0).getId()));
		m.put("MejorJugador",repoNParticipante.getTopByPremioNombrePremioAndPremioTieneIdOrderByCantidadVotosDesc("Mejor jugador de eSports", galas.get(0).getId()));
		m.put("MejorProduccion",repoNParticipante.getTopByPremioNombrePremioAndPremioTieneIdOrderByCantidadVotosDesc("Mejor produccion audiovisual", galas.get(0).getId()));
		
		
		m.put("view","gala/galaActiva");
		return "_t/frame";
	}
}
