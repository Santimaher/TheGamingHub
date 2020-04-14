package org.tfgdp2.com.controller;

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
import org.tfgdp2.com.helper.PRG;
import org.tfgdp2.com.repository.JuegoRepository;
import org.tfgdp2.com.repository.NominacionJuegoRepository;
import org.tfgdp2.com.repository.NominacionParticipanteRepository;
import org.tfgdp2.com.repository.ParticipanteRepository;
import org.tfgdp2.com.repository.PremioJuegoRepository;
import org.tfgdp2.com.repository.PremioParticipanteRepository;

@Controller
@RequestMapping(value =  "/nominacion")
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
	
	
	@GetMapping("nominarParticipante")
	public String nominarParticipante(ModelMap m) {
		m.put("participantes", repoParticipante.findAll());
		m.put("premioP", repoPremioP.findAll());
		m.put("view", "nominacion/nominarParticipante");
		return "_t/frame";
	}
	
	@PostMapping("nominarParticipante")
	public void nominarPpost(@RequestParam("id") Long idParticipante,@RequestParam("premio") Long idPremio) throws InfoException, DangerException {
		Participante p = repoParticipante.getOne(idParticipante);
		try {
		Premio_Participante pr = repoPremioP.getOne(idPremio);
		p.setEstaNominado(true);
		Nominacion_Participante np = new Nominacion_Participante();
		np.setNombre(p.getNombre());
		np.setPremio(pr);
		pr.getPremiados().add(np);
		repoNP.save(np);
		}		
		catch (Exception e) {
			PRG.error("El partipante no se pudo nominar");
		}
		PRG.info("Participante nominado correctamente.");
	}
	
	@GetMapping("nominarJuego")
	public String nominarJuego(ModelMap m) {
		m.put("juegos",repoJuego.findAll());
		m.put("premioJ", repoPremioJ.findAll());
		m.put("view", "nominacion/nominarJuego");
		return ("_t/frame");
	}
	
	@PostMapping("nominarJuego")
	public void nominarJpost(@RequestParam("id") Long idJuego,@RequestParam("premio") Long idPremio) throws DangerException, InfoException{
		Juego j = repoJuego.getOne(idJuego);
		try {
		Premio_Juego pr = repoPremioJ.getOne(idPremio);
		j.setEstaNominado(true);
		Nominacion_Juego nj = new Nominacion_Juego();
		nj.setNombre(j.getNombre());
		nj.setPremio(pr);
		pr.getPremiados().add(nj);
		repoNJ.save(nj);
		}		
		catch (Exception e) {
			PRG.error("El juego no se pudo nominar");
		}
		PRG.info("Juego nominado correctamente.");
	}
}
