package org.tfgdp2.com.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tfgdp2.com.repository.EntradaForoRepository;
import org.tfgdp2.com.repository.ForoRepository;
import org.tfgdp2.com.repository.JuegoRepository;

@Controller
@RequestMapping(value="/foro")
public class ForoController {

	@Autowired
	private ForoRepository repoForo;
	
	@Autowired
	private EntradaForoRepository repoEntrada;
	
	@Autowired
	private JuegoRepository repoJuego;
	
	@GetMapping("r")
	public String read(ModelMap m,@RequestParam("id") Long id) {
		m.put("juego", repoJuego.getOne(id));
		m.put("foros", repoForo.findAllByJuego_id(id));
		m.put("view", "foro/r");
		return "_t/frame";
	}
	@GetMapping("r2")
	public String read2(ModelMap m) {
		m.put("fanarts", repoForo.findAllByTipo("Fan art"));
		m.put("debugs", repoForo.findAllByTipo("Debug"));
		m.put("memes", repoForo.findAllByTipo("Memes"));
		m.put("miscs", repoForo.findAllByTipo("Miscelaneo"));
		m.put("view", "foro/r2");
		return "_t/frame";
	}

}
