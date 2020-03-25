package org.tfgdp2.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tfgdp2.com.domain.Categoria;
import org.tfgdp2.com.domain.Juego;
import org.tfgdp2.com.domain.Plataforma;
import org.tfgdp2.com.repository.CategoriaRepository;
import org.tfgdp2.com.repository.JuegoRepository;
import org.tfgdp2.com.repository.PlataformaRepository;

@Controller
@RequestMapping(value = "/juego")
public class JuegoController {
	@Autowired
	private JuegoRepository repoJuego;
	@Autowired
	private CategoriaRepository repoCat;
	@Autowired
	private PlataformaRepository repoPlat;

	@GetMapping("r")
	public String read(ModelMap m) {
		List<Juego> juegos = repoJuego.findAll();
		m.put("juegos", juegos);
		m.put("view", "/juego/R");
		return "/_t/frame";
	}
	
	@GetMapping("c")
	public String cGet(ModelMap m) {
		List<Categoria> categorias = repoCat.findAll();
		List<Plataforma> plataformas = repoPlat.findAll();
		m.put("categorias", categorias);
		m.put("plataformas", plataformas);
		m.put("view", "/juego/c");
		return "/_t/frame";
	}

}
