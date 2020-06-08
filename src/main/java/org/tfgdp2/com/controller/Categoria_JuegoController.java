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
import org.tfgdp2.com.domain.Categoria_Juego;
import org.tfgdp2.com.exception.DangerException;
import org.tfgdp2.com.exception.InfoException;
import org.tfgdp2.com.helper.H;
import org.tfgdp2.com.helper.PRG;
import org.tfgdp2.com.repository.Categoria_JuegoRepository;

@Controller
@RequestMapping(value = "/categoria")
public class Categoria_JuegoController {

	@Autowired
	private Categoria_JuegoRepository repoCategoria_Juego;

	@GetMapping("r")
	public String read(ModelMap m) {
		List<Categoria_Juego> categorias = repoCategoria_Juego.findAll();
		m.put("categorias", categorias);
		m.put("view", "categoria/R");
		return "_t/frame";
	}

	@GetMapping("c")
	public String cGet(ModelMap m, HttpSession s) throws DangerException {
		H.isRolOK("admin", s);
		m.put("view", "categoria/C");
		return "_t/frame";
	}

	@PostMapping("c")
	public void crearPost(ModelMap m, @RequestParam("nombre") String nombre, HttpSession s)
			throws DangerException, InfoException {
		try {
			H.isRolOK("admin", s);
			Categoria_Juego cat = new Categoria_Juego();
			cat.setNombre(nombre);
			repoCategoria_Juego.save(cat);
		} catch (Exception e) {
			PRG.error("Categoria " + nombre + " duplicada", "categoria/c");
		}
		PRG.info("Categoria " + nombre + " creada correctamente", "categoria/r");
	}

	@GetMapping("u")
	public String uGet(@RequestParam("id") Long idCategoria, ModelMap m, HttpSession s) throws DangerException {
		H.isRolOK("admin", s);
		m.put("categoria", repoCategoria_Juego.getOne(idCategoria));
		m.put("view", "categoria/U");
		return "_t/frame";
	}

	@PostMapping("u")
	public void uPost(@RequestParam("nombre") String nombreCategoria, @RequestParam("id") Long idCategoria,
			HttpSession s) throws DangerException, InfoException {

		try {
			H.isRolOK("admin", s);
			Categoria_Juego cat = repoCategoria_Juego.getOne(idCategoria);
			cat.setNombre(nombreCategoria);

			repoCategoria_Juego.save(cat);

		} catch (Exception e) {
			PRG.error("Categoria " + nombreCategoria + " duplicada", "categoria/r");
		}
		PRG.info("Categoria " + nombreCategoria + " actualizada correctamente", "categoria/r");
	}

	@PostMapping("d")
	public void dPost(@RequestParam("id") Long id, ModelMap m, HttpSession s) throws DangerException, InfoException {

		try {
			H.isRolOK("admin", s);
			repoCategoria_Juego.delete(repoCategoria_Juego.getOne(id));
		} catch (Exception e) {
			PRG.error("Error al borrar la Categoria", "categoria/r");
		}

		PRG.info("Categoria borrada correctamente", "categoria/r");

	}

}
