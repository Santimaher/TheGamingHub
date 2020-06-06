package org.tfgdp2.com.controller;

import java.util.Collection;
import java.util.HashSet;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tfgdp2.com.domain.Juego;
import org.tfgdp2.com.domain.Nominacion_Juego;
import org.tfgdp2.com.domain.Nominacion_Participante;
import org.tfgdp2.com.domain.Participante;
import org.tfgdp2.com.domain.Premio_Juego;
import org.tfgdp2.com.domain.Premio_Participante;
import org.tfgdp2.com.exception.DangerException;
import org.tfgdp2.com.exception.InfoException;
import org.tfgdp2.com.helper.H;
import org.tfgdp2.com.helper.PRG;
import org.tfgdp2.com.repository.GalaRepository;
import org.tfgdp2.com.repository.JuegoRepository;
import org.tfgdp2.com.repository.NominacionJuegoRepository;
import org.tfgdp2.com.repository.NominacionParticipanteRepository;
import org.tfgdp2.com.repository.ParticipanteRepository;
import org.tfgdp2.com.repository.PremioJuegoRepository;
import org.tfgdp2.com.repository.PremioParticipanteRepository;

@Controller
@RequestMapping(value = "/nominacion")
public class NominacionController {

	@Autowired
	private ParticipanteRepository repoParticipante;

	@Autowired
	private JuegoRepository repoJuego;

	@Autowired
	private PremioJuegoRepository repoPremioJ;

	@Autowired
	private PremioParticipanteRepository repoPremioP;

	@Autowired
	private NominacionParticipanteRepository repoNP;

	@Autowired
	private NominacionJuegoRepository repoNJ;

	@Autowired
	private GalaRepository repoGala;

	@GetMapping("nominarParticipante")
	public String nominarParticipante(ModelMap m, HttpSession s) throws DangerException {
		H.isRolOK("jurado", s);
		m.put("participantes", repoParticipante.findAll());
		m.put("premioP", repoPremioP.findByTiene_id(repoGala.findTopByOrderByIdDesc().getId()));
		m.put("view", "nominacion/nominarParticipante");
		return "_t/frame";
	}

	@PostMapping("nominarParticipante")
	public void nominarPpost(HttpSession s, @RequestParam("id") Long idParticipante,
			@RequestParam("premio") Long idPremio) throws InfoException, DangerException {
		Participante p = repoParticipante.getOne(idParticipante);
		try {
			if (estaNominadoEnEsePremio(false, idParticipante, idPremio)) {
				PRG.error("Este juego ya esta nominado en este premio", "nominacion/nominarParticipante");
			} else {

			}
			H.isRolOK("jurado", s);
			Premio_Participante pr = repoPremioP.getOne(idPremio);
			p.setEstaNominado(true);
			Nominacion_Participante np = new Nominacion_Participante();
			np.setNombre(p.getNombre());
			np.setPremio(pr);
			pr.getPremiados().add(np);
			np.getParticipantes().add(p);
			p.getNominado().add(np);
			repoNP.save(np);
		} catch (Exception e) {
			PRG.error("El partipante no se pudo nominar");
		}
		PRG.info("Participante nominado correctamente.", "nominacion/nominarParticipante");
	}

	@GetMapping("nominarJuego")
	public String nominarJuego(ModelMap m, HttpSession s,
			@RequestParam(value = "filtro", required = false) String filtro) throws DangerException {
		filtro = (filtro == null) ? "" : filtro;
		try {
			H.isRolOK("jurado", s);
		} catch (DangerException e) {
			PRG.error("Rol inadecuado", "/");
		}
		m.put("juegos", repoJuego.findByNombreStartsWithIgnoreCase(filtro));
		m.put("premioJ", repoPremioJ.findByTiene_id(repoGala.findTopByOrderByIdDesc().getId()));
		m.put("filtro", filtro);
		m.put("view", "nominacion/nominarJuego");
		return ("_t/frame");
	}

	@PostMapping("nominarJuego")
	public void nominarJpost(HttpSession s, @RequestParam("id") Long idJuego, @RequestParam("premio") Long idPremio)
			throws DangerException, InfoException {
		Juego j = repoJuego.getOne(idJuego);
		try {
			H.isRolOK("jurado", s);
			if (estaNominadoEnEsePremio(true, idJuego, idPremio)) {
				PRG.error("Este juego ya esta nominado en este premio", "nominacion/nominarJuego");
			} else {
				Premio_Juego pr = repoPremioJ.getOne(idPremio);
				j.setEstaNominado(true);
				Nominacion_Juego nj = new Nominacion_Juego();
				nj.setNombre(j.getNombre());
				nj.setPremio(pr);
				pr.getPremiados().add(nj);
				nj.getJuegos().add(j);
				j.getNominado().add(nj);
				repoNJ.save(nj);
			}

		} catch (Exception e) {
			PRG.error("El juego no se pudo nominar");
		}
		PRG.info("Juego nominado correctamente.", "nominacion/nominarJuego");
	}

	@GetMapping("npr")
	public String nominacionParticipanteR(ModelMap m) {
		m.put("participantes", repoNP.findAll());
		m.put("view", "nominacion/nPr");
		return "_t/frame";
	}

	@GetMapping("njr")
	public String nominacionJuegoR(ModelMap m) {
		m.put("juegos", repoNJ.findAll());
		m.put("view", "nominacion/nJr");
		return "_t/frame";
	}

	public boolean estaNominadoEnEsePremio(boolean esJuego, Long idAspirante, Long idPremio) {
		boolean check = false;
		if (esJuego) {
			Juego J = repoJuego.getOne(idAspirante);
			HashSet<Long> listadoAsp = new HashSet<>();
			Collection<Nominacion_Juego> listaNJ = J.getNominado();
			for (Nominacion_Juego nominacion_Juego : listaNJ) {
				listadoAsp.add(nominacion_Juego.getPremio().getId());
			}
			HashSet<Long> listadoPJ = new HashSet<>();
			Premio_Juego pjs = repoPremioJ.getOne(idPremio);
			Collection<Nominacion_Juego> njs = pjs.getPremiados();
			for (Nominacion_Juego idInd : njs) {
				listadoPJ.add(idInd.getId());
			}
			for (Long long1 : listadoPJ) {
				check = listadoAsp.contains(long1);
			}

		} else {
			Participante P = repoParticipante.getOne(idAspirante);
			HashSet<Long> listadoAsp2 = new HashSet<>();
			Collection<Nominacion_Participante> listaNP = P.getNominado();
			for (Nominacion_Participante nominacion_Participante : listaNP) {
				listadoAsp2.add(nominacion_Participante.getPremio().getId());
			}
			HashSet<Long> listadoPP = new HashSet<>();
			Premio_Participante pps = repoPremioP.getOne(idPremio);
			Collection<Nominacion_Participante> nps = pps.getPremiados();
			for (Nominacion_Participante idInd : nps) {
				listadoPP.add(idInd.getId());
			}
			for (Long long1 : listadoPP) {
				check = listadoAsp2.contains(long1);
			}
		}

		return check;
	}
}
