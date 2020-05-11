package org.tfgdp2.com.controller;

import java.util.Collection;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tfgdp2.com.domain.Gala;
import org.tfgdp2.com.domain.Nominacion_Juego;
import org.tfgdp2.com.domain.Nominacion_Participante;
import org.tfgdp2.com.domain.Premio_Juego;
import org.tfgdp2.com.domain.Premio_Participante;
import org.tfgdp2.com.domain.Usuario;
import org.tfgdp2.com.exception.DangerException;
import org.tfgdp2.com.exception.InfoException;
import org.tfgdp2.com.helper.PRG;
import org.tfgdp2.com.repository.GalaRepository;
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

	@Autowired
	private GalaRepository repoGala;

	@Autowired
	private NominacionParticipanteRepository repoNP;

	@Autowired
	private NominacionJuegoRepository repoNJ;

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
				Gala g = repoGala.findTopByOrderByEdicionDesc();
				g.getPremiosP().add(pp);
				repoPremioPar.save(pp);
			} catch (Exception e) {
				PRG.error("Error al crear el premio. " + e.getMessage() + "", "/premio/c");
			}
		}
		if (tipo.equals("juego")) {
			try {
				Premio_Juego pj = new Premio_Juego(nombre);
				Gala g = repoGala.findTopByOrderByEdicionDesc();
				g.getPremiosJ().add(pj);
				repoPremioJuego.save(pj);
			} catch (Exception e) {
				PRG.error("Error al crear el premio. " + e.getMessage() + "", "/premio/c");
			}
		}

		PRG.info("Premio creado", "/premio/r");
	}

	@GetMapping("r")
	public String r(ModelMap m) {

		m.put("premioPartipantes", repoPremioPar.findAll());
		m.put("premioJuegos", repoPremioJuego.findAll());
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
	public String addVotoP(ModelMap m, @RequestParam("id") Long id,HttpSession s) throws DangerException {
		String vista = null;
		if (haVotado(s, id, false)) {
			PRG.error("Ya ha votado en este premio", "/premio/r");
			
		} else {
			m.put("premio", repoPremioPar.getOne(id));
			m.put("nominados", repoNP.findByPremioId(id));
			m.put("view", "premio/addVotoP");
			vista =  "_t/frame";
		}
		
		return vista;
		
	}

	@PostMapping("addVotoP")
	public void addVotoPost(@RequestParam("id") Long idNominado, HttpSession s) throws DangerException, InfoException {
		Usuario u = (Usuario) s.getAttribute("usuario");
		Nominacion_Participante np = repoNP.getOne(idNominado);
//		Votacion_Participante vp = new Votacion_Participante(np, u);
		try {
			np.setCantidadVotos(np.getCantidadVotos() + 1);
			u.getVotadosP().add(np);
			np.getVotacionesP().add(u);
			repoNP.save(np);
		} catch (Exception e) {
			PRG.info("Fallo al guardar su voto"+e.getMessage(),"/premio/r");
		}

		PRG.info("Su voto ha sido guardado","/premio/r");
	}

	@GetMapping("addVotoJ")
	public String addVotoJ(ModelMap m, @RequestParam("id") Long id,HttpSession s) throws DangerException {
		String vista = null;
		if (haVotado(s, id, true)) {
			PRG.error("Ya ha votado en este premio", "/premio/r");
		} else {
			m.put("premio", repoPremioJuego.getOne(id));
			m.put("nominados", repoNJ.findByPremioId(id));
			m.put("view", "premio/addVotoJ");
			vista= "_t/frame";
		}
		return vista;
	}

	@PostMapping("addVotoJ")
	public void addVotoJPost(@RequestParam("id") Long idNominado, HttpSession s) throws DangerException, InfoException {
		Usuario u = (Usuario) s.getAttribute("usuario");
		Nominacion_Juego nj = repoNJ.getOne(idNominado);
		try {			
			nj.setCantidadVotos(nj.getCantidadVotos() + 1);
			u.getVotadosJ().add(nj);
			nj.getVotacionesJ().add(u);
			repoNJ.save(nj);
		} catch (Exception e) {
			PRG.info("Fallo al guardar su voto"+e.getMessage(),"/premio/r");
		}

		PRG.info("Su voto ha sido guardado","/premio/r");
	}
	
	public boolean haVotado(HttpSession s, Long idPremio, boolean isJuego) {
		Usuario usu = (Usuario) s.getAttribute("usuario");
		boolean check = false;
		if (isJuego) {
			Collection<Nominacion_Juego>votados=usu.getVotadosJ();
			if (votados.isEmpty()) {
				check=false;
			}
			else {			
			Collection<Nominacion_Juego> nominaciones = repoNomJuego.findByPremioId(idPremio);
			for (Nominacion_Juego nominado : nominaciones) {
				for (Nominacion_Juego votado : votados) {
					if(votado.getId().equals(nominado.getId())) {
						check = true;
					}
					else {
						check = false;
					}
				}
			}
			}
		} else {
			Collection<Nominacion_Participante>votados=usu.getVotadosP();
			if (votados.isEmpty()) {
				check=false;
			}
			else {
			Collection<Nominacion_Participante> nominaciones = repoNomPar.findByPremioId(idPremio);
			for (Nominacion_Participante nominado : nominaciones) {
				for (Nominacion_Participante votado : votados) {
					if(votado.getId().equals(nominado.getId())) {
						check = true;
					}
					else {
						check = false;
					}
				}
			}
		}
		}	
		return check;
	}
}
