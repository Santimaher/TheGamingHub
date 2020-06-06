package org.tfgdp2.com.controller;

import java.time.LocalDate;
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
import org.tfgdp2.com.domain.Gala;
import org.tfgdp2.com.domain.Nominacion_Juego;
import org.tfgdp2.com.domain.Nominacion_Participante;
import org.tfgdp2.com.domain.Premio_Juego;
import org.tfgdp2.com.domain.Premio_Participante;
import org.tfgdp2.com.domain.Usuario;
import org.tfgdp2.com.exception.DangerException;
import org.tfgdp2.com.exception.InfoException;
import org.tfgdp2.com.helper.H;
import org.tfgdp2.com.helper.PRG;
import org.tfgdp2.com.repository.GalaRepository;
import org.tfgdp2.com.repository.NominacionJuegoRepository;
import org.tfgdp2.com.repository.NominacionParticipanteRepository;
import org.tfgdp2.com.repository.PremioJuegoRepository;
import org.tfgdp2.com.repository.PremioParticipanteRepository;
import org.tfgdp2.com.repository.UsuarioRepository;

@Controller
@RequestMapping("/premio")
public class PremioController {

	@Autowired
	private PremioParticipanteRepository repoPremioPar;

	@Autowired
	private PremioJuegoRepository repoPremioJuego;

	@Autowired
	private GalaRepository repoGala;

	@Autowired
	private NominacionParticipanteRepository repoNP;

	@Autowired
	private NominacionJuegoRepository repoNJ;

	@Autowired
	private UsuarioRepository repoUsu;

	@GetMapping("c")
	public String cGet(ModelMap m, HttpSession s) throws DangerException {
		try {
			H.isRolOK("admin", s);
			m.put("view", "premio/c");
		} catch (Exception e) {
			m.put("view", "home");
		}
		return "_t/frame";
	}

	@PostMapping("c")
	public void cPost(@RequestParam("nombre") String nombre, @RequestParam("tipo") String tipo, HttpSession s)
			throws DangerException, InfoException {

		if (tipo.equals("participante")) {
			try {
				H.isRolOK("admin", s);
				Premio_Participante pp = new Premio_Participante(nombre);
				Gala g = repoGala.findTopByOrderByEdicionDesc();
				g.getPremiosP().add(pp);
				repoPremioPar.save(pp);
			} catch (Exception e) {
				PRG.error("Error al crear el premio. " + e.getMessage() + "", "premio/c");
			}
		}
		if (tipo.equals("juego")) {
			try {
				H.isRolOK("admin", s);
				Premio_Juego pj = new Premio_Juego(nombre);
				Gala g = repoGala.findTopByOrderByEdicionDesc();
				g.getPremiosJ().add(pj);
				repoPremioJuego.save(pj);
			} catch (Exception e) {
				PRG.error("Error al crear el premio. " + e.getMessage() + "", "premio/c");
			}
		}

		PRG.info("Premio creado", "premio/r");
	}

	@GetMapping("r")
	public String r(ModelMap m) throws DangerException {
		try {
			Gala g = repoGala.getByActivoTrue();
			m.put("gala", g);
			m.put("view", "premio/r");

		} catch (NullPointerException e) {
			PRG.error("Inicie sesion para votar", "/login");
		}
		return "_t/frame";

	}

	@GetMapping("rAdmin")
	public String rAdmin(ModelMap m, HttpSession s) throws DangerException {
		try {
			H.isRolOK("admin", s);
			m.put("pj", repoPremioJuego.findAll());
			m.put("pp", repoPremioPar.findAll());
			m.put("view", "premio/rAdmin");

		} catch (NullPointerException e) {
			PRG.error("Inicie sesion para votar", "/login");
		}
		return "_t/frame";

	}

	@PostMapping("dP")
	public void deleteP(@RequestParam("id") Long id, HttpSession s) throws DangerException, InfoException {
		try {
			H.isRolOK("admin", s);
			Premio_Participante n = repoPremioPar.getOne(id);
			repoPremioPar.delete(n);

		} catch (Exception e) {
			PRG.error("Error al eliminar el premio", "premio/rAdmin");
		}
		PRG.info("Premio borrado correctamente", "premio/rAdmin");
	}

	@PostMapping("dJ")
	public void deleteJ(@RequestParam("id") Long id, HttpSession s) throws DangerException, InfoException {
		try {
			H.isRolOK("admin", s);
			Premio_Juego njuego = repoPremioJuego.getOne(id);
			repoPremioJuego.delete(njuego);

		} catch (Exception e) {
			PRG.error("Error al eliminar el premio", "premio/r");
		}
		PRG.info("Premio borrado correctamente", "premio/r");
	}

	@GetMapping("addVotoP")
	public String addVotoP(ModelMap m, @RequestParam("id") Long id, HttpSession s) throws DangerException {
		String vista = null;
		Gala g = repoGala.findTopByOrderByEdicionDesc();
		try {
			Usuario usu = (Usuario) s.getAttribute("usuario");
			if (haVotado(usu.getId(), id, false)) {
				PRG.error("Ya ha votado en este premio", "/premio/r");
				if (g.getActivo() == true) {
					PRG.error("La gala no esta activa", "premio/r");
					if (g.getFin().isAfter(LocalDate.now())) {
						PRG.error("El tiempo de votacion ha excedido", "premio/r");
					}
				}
			} else {
				m.put("premio", repoPremioPar.getOne(id));
				m.put("nominados", repoNP.findByPremioId(id));
				m.put("view", "premio/addVotoP");
				vista = "_t/frame";
			}
		} catch (NullPointerException e) {
			PRG.error("Inicie sesion para votar", "/login");
		}

		return vista;

	}

	@GetMapping("addVotoJ")
	public String addVotoJ(ModelMap m, @RequestParam("id") Long id, HttpSession s) throws DangerException {
		String vista = null;
		Gala g = repoGala.findTopByOrderByEdicionDesc();
		try {
			Usuario usu = (Usuario) s.getAttribute("usuario");
			if (haVotado(usu.getId(), id, true)) {
				PRG.error("Ya ha votado en este premio", "premio/r");
				if (g.getActivo() == true) {
					PRG.error("La gala no esta activa", "premio/r");
					if (g.getFin().isAfter(LocalDate.now())) {
						PRG.error("El tiempo de votacion ha excedido", "premio/r");
					}
				}

			} else {
				m.put("premio", repoPremioJuego.getOne(id));
				m.put("nominados", repoNJ.findByPremioId(id));
				m.put("view", "premio/addVotoJ");
				vista = "_t/frame";
			}
		} catch (NullPointerException e) {
			PRG.error("Inicie sesion para votar", "login");
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
			PRG.info("Fallo al guardar su voto", "premio/r");
		}

		PRG.info("Su voto ha sido guardado", "premio/r");
	}

	@PostMapping("addVotoP")
	public void addVotoPost(@RequestParam("id") Long idNominado, HttpSession s) throws DangerException, InfoException {
		Usuario u = (Usuario) s.getAttribute("usuario");
		Nominacion_Participante np = repoNP.getOne(idNominado);
		try {
			np.setCantidadVotos(np.getCantidadVotos() + 1);
			u.getVotadosP().add(np);
			np.getVotacionesP().add(u);
			repoNP.save(np);
		} catch (Exception e) {
			PRG.info("Fallo al guardar su voto", "premio/r");
		}

		PRG.info("Su voto ha sido guardado", "premio/r");
	}

	public boolean haVotado(Long idUsu, Long idPremio, boolean isJuego) {
		Usuario usu = repoUsu.getOne(idUsu);
		boolean check = false;
		if (isJuego) {
			Collection<Nominacion_Juego> votados = usu.getVotadosJ();
			if (votados.isEmpty()) {
				check = false;
			} else {
				HashSet<Long> listadoVotados = new HashSet<>();
				for (Nominacion_Juego votado : votados) {
					listadoVotados.add(votado.getPremio().getId());
				}
				check = listadoVotados.contains(idPremio);
			}

		} else {
			Collection<Nominacion_Participante> votados = usu.getVotadosP();
			if (votados.isEmpty()) {
				check = false;
			} else {
				HashSet<Long> listadoVotados = new HashSet<>();
				for (Nominacion_Participante votado : votados) {
					listadoVotados.add(votado.getPremio().getId());
				}
				check = listadoVotados.contains(idPremio);
			}
		}

		return check;
	}
}
