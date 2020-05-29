package org.tfgdp2.com.controller;

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
	public String nominarParticipante(ModelMap m,HttpSession s) throws DangerException {
		H.isRolOK("jurado", s);
		m.put("participantes", repoParticipante.findAll());
		m.put("premioP", repoPremioP.findAll());
		m.put("view", "nominacion/nominarParticipante");
		return "_t/frame";
	}
	
	@PostMapping("nominarParticipante")
	public void nominarPpost(HttpSession s,@RequestParam("id") Long idParticipante,@RequestParam("premio") Long idPremio) throws InfoException, DangerException {
		Participante p = repoParticipante.getOne(idParticipante);
		try {
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
		}		
		catch (Exception e) {
			PRG.error("El partipante no se pudo nominar");
		}
		PRG.info("Participante nominado correctamente.");
	}
	
	@GetMapping("nominarJuego")
	public String nominarJuego(ModelMap m,HttpSession s) throws DangerException {
		try{H.isRolOK("jurado", s);}
		catch (Exception e) {
			PRG.error("Rol inadecuado");
		}
		m.put("juegos",repoJuego.findAll());
		m.put("premioJ", repoPremioJ.findAll());
		m.put("view", "nominacion/nominarJuego");
		return ("_t/frame");
	}
	
	@PostMapping("nominarJuego")
	public void nominarJpost(@RequestParam("id") Long idJuego,@RequestParam("premio") Long idPremio,HttpSession s) throws DangerException, InfoException{
		Juego j = repoJuego.getOne(idJuego);
		try {
			H.isRolOK("jurado", s);
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
		catch (Exception e) {
			PRG.error("El juego no se pudo nominar");
		}
		PRG.info("Juego nominado correctamente.","nominacion/nominacioJuego");
	}
	
	@GetMapping("npr")
	public String nominacionParticipanteR(ModelMap m){
		m.put("participantes",repoNP.findAll());
		m.put("view", "nominacion/nPr");
		return "_t/frame";
	}
	
	@GetMapping("njr")
	public String nominacionJuegoR(ModelMap m) {
		m.put("juegos", repoNJ.findAll());
		m.put("view", "nominacion/nJr");
		return "_t/frame";
	}
}
