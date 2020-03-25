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
import org.tfgdp2.com.domain.Categoria;
import org.tfgdp2.com.domain.Plataforma;
import org.tfgdp2.com.exception.DangerException;
import org.tfgdp2.com.exception.InfoException;
import org.tfgdp2.com.helper.PRG;
import org.tfgdp2.com.repository.CategoriaRepository;

@Controller
@RequestMapping(value = "/categoria")
public class CategoriaController {

	@Autowired
	private CategoriaRepository repoCategoria;
	
	@GetMapping("r")
	public String read(ModelMap m) {
		List<Categoria> categorias = repoCategoria.findAll();
		m.put("categorias", categorias);
		m.put("view", "/categoria/R");
		return "/_t/frame";
	}
	
	@GetMapping("c")
	public String cGet(ModelMap m) {
		m.put("view", "/categoria/c");
		return "/_t/frame";
	}
	
	@PostMapping("c")
	public void crearPost(ModelMap m, @RequestParam("nombre") String nombre, HttpSession s) throws DangerException, InfoException {
		try {
			Categoria cat = new Categoria();
			cat.setNombre(nombre);
			repoCategoria.save(cat);
		} catch (Exception e) {
			PRG.error("Categoria " + nombre + " duplicada", "/categoria/c");
		}
		PRG.info("Categoria " + nombre + " creada correctamente", "/categoria/r");
	}
	
	@GetMapping("u")
	public String uGet(@RequestParam("id") Long idCategoria, ModelMap m, HttpSession s) throws DangerException {
		
		m.put("categoria", repoCategoria.getOne(idCategoria));
		m.put("view", "/categoria/U");
		return "/_t/frame";
	}

	@PostMapping("u")
	public void uPost(@RequestParam("nombre") String nombreCategoria, @RequestParam("id") Long idCategoria, HttpSession s)
			throws DangerException, InfoException {
		
		try {
			Categoria cat = repoCategoria.getOne(idCategoria);
			cat.setNombre(nombreCategoria);
		
			repoCategoria.save(cat);
			
		} catch (Exception e) {
			PRG.error("Categoria " + nombreCategoria + " duplicada", "/categoria/r");
		}
		PRG.info("Categoria " + nombreCategoria + " actualizada correctamente", "/categoria/r");
	}
	
	@PostMapping("d")
	public String dPost(@RequestParam("id") Long id, ModelMap m, HttpSession s) throws DangerException {

		
		try {
			repoCategoria.delete(repoCategoria.getOne(id));
		} catch (Exception e) {
			PRG.error("Error al borrar la Categoria", "/categoria/r");
		}

		return "redirect:/plataforma/r";

	}
}
