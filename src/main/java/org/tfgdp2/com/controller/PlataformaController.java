package org.tfgdp2.com.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tfgdp2.com.domain.Plataforma;
import org.tfgdp2.com.exception.DangerException;
import org.tfgdp2.com.exception.InfoException;
import org.tfgdp2.com.helper.PRG;
import org.tfgdp2.com.repository.PlataformaRepository;

@Controller
@RequestMapping(value = "/plataforma")
public class PlataformaController {
	
	@Autowired
	private PlataformaRepository repoPlataforma;
	
	@GetMapping("r")
	public String read(ModelMap m) {
		List<Plataforma> plataformas = repoPlataforma.findAll();
		m.put("plataformas", plataformas);	
		m.put("view","/plataforma/R");
		return "/_t/frame";
	}
	@GetMapping("c")
	public String cGet(ModelMap m ) {
		m.put("view", "/plataforma/c");
		return "/_t/frame";	
	}
	
	@PostMapping("c")
	public void crearPost(ModelMap m, @RequestParam("nombre") String nombre, @RequestParam("imagen") String imagen,HttpSession s) throws DangerException, InfoException {
		try {
			Plataforma plataforma = new Plataforma();
			plataforma.setNombre(nombre);
			plataforma.setImg(imagen);
			repoPlataforma.save(plataforma);
		} catch (Exception e) {
			PRG.error("Plataforma " + nombre + " duplicado", "/plataforma/c");
		}
		PRG.info("Plataforma " + nombre + " creada correctamente", "/plataforma/r");
	}

}
