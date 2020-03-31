package org.tfgdp2.com.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


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
	public String read(ModelMap m) {
		
		return "/_t/frame";
	}

}
