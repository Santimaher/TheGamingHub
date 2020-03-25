package org.tfgdp2.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tfgdp2.com.domain.Juego;
import org.tfgdp2.com.repository.JuegoRepository;

@Controller
@RequestMapping(value = "/juego")
public class JuegoController {
	@Autowired
	private JuegoRepository repoJuego;

	@GetMapping("r")
	public String read(ModelMap m) {
		List<Juego> juegos = repoJuego.findAll();
		m.put("juegos", juegos);
		m.put("view", "/juego/R");
		return "/_t/frame";
	}

}
