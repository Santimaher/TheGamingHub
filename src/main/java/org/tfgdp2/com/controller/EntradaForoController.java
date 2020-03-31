package org.tfgdp2.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tfgdp2.com.repository.EntradaForoRepository;

@Controller
@RequestMapping(value= "/entradaForo")
public class EntradaForoController {
	
	@Autowired
	private EntradaForoRepository repoEntrada;
	
	@GetMapping("c")
	public String crear(ModelMap m) {
		m.put("view", "/entradaForo/c");
		return "_t/frame";
	}
	@PostMapping("c")
	public String cPost(@RequestParam("comentario") String comentario){
		
		
		return "redirect:/entradaForo/r";
	}
	
	
	

}
