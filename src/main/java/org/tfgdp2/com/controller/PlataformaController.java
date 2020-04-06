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
import org.tfgdp2.com.helper.H;
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
		m.put("view", "/plataforma/R");
		return "/_t/frame";
	}

	@GetMapping("c")
	public String cGet(ModelMap m,HttpSession s) throws DangerException {
		H.isRolOK("admin", s);
		m.put("view", "/plataforma/c");
		return "/_t/frame";
	}

	@PostMapping("c")
	public void crearPost(ModelMap m, @RequestParam("nombre") String nombre, @RequestParam("imagen") String imagen,
			HttpSession s) throws DangerException, InfoException {
		
		try {
			H.isRolOK("admin", s);
			Plataforma plataforma = new Plataforma();
			plataforma.setNombre(nombre);
			plataforma.setImg(imagen);
			repoPlataforma.save(plataforma);
		} catch (Exception e) {
			PRG.error("Plataforma " + nombre + " duplicado", "/plataforma/c");
		}
		PRG.info("Plataforma " + nombre + " creada correctamente", "/plataforma/r");
	}
	
	@GetMapping("u")
	public String uGet(@RequestParam("id") Long idPlataforma, ModelMap m, HttpSession s) throws DangerException {
		H.isRolOK("admin", s);
		m.put("plataforma", repoPlataforma.getOne(idPlataforma));
		m.put("view", "/plataforma/U");
		return "/_t/frame";
	}

	@PostMapping("u")
	public void uPost(@RequestParam("nombre") String nombrePlataforma,@RequestParam("imagen") String imagen, @RequestParam("id") Long idPlataforma, HttpSession s)
			throws DangerException, InfoException {
		
		try {
			H.isRolOK("admin", s);
			Plataforma p = repoPlataforma.getOne(idPlataforma);
			p.setNombre(nombrePlataforma);
			p.setImg(imagen);
			repoPlataforma.save(p);
			
		} catch (Exception e) {
			PRG.error("Plataforma " + nombrePlataforma + " duplicada", "/plataforma/r");
		}
		PRG.info("Plataforma " + nombrePlataforma + " actualizada correctamente", "/plataforma/r");
	}
	
	@PostMapping("d")
	public void dPost(@RequestParam("id") Long id, ModelMap m, HttpSession s) throws DangerException, InfoException {
	
		
		try {
			H.isRolOK("admin", s);
			repoPlataforma.delete(repoPlataforma.getOne(id));
		} catch (Exception e) {
			PRG.error("Error al borrar la Plataforma", "/plataforma/r");
		}

		PRG.info("Plataforma borrada correctamente", "/plataforma/r");

	}

}
