package org.tfgdp2.com.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tfgdp2.com.domain.Categoria;
import org.tfgdp2.com.domain.Foro;
import org.tfgdp2.com.domain.Juego;
import org.tfgdp2.com.domain.Plataforma;
import org.tfgdp2.com.exception.DangerException;
import org.tfgdp2.com.exception.InfoException;
import org.tfgdp2.com.helper.PRG;
import org.tfgdp2.com.repository.CategoriaRepository;
import org.tfgdp2.com.repository.ForoRepository;
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
	@Autowired
	private ForoRepository repoForo;
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

	@PostMapping("c")
	public void cPost(@RequestParam("nombre") String nombre, @RequestParam("precio") Double precio,
			@RequestParam("stock") Integer stock, @RequestParam("desarrolladora") String desarrolladora,
			@RequestParam("img") String img, @RequestParam("teaser") String teaser,
			@RequestParam(value = "idPlataforma[]") List<Long> idsPlataforma,
			@RequestParam(value = "idCat[]") List<Long> idsCat,
			@RequestParam("flan") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate flan, HttpSession s)
			throws DangerException, InfoException {
		try {
			Juego j = new Juego();
			j.setNombre(nombre);
			j.setPrecio(precio);
			j.setStock(stock);
			j.setDesarrolladora(desarrolladora);
			j.setImg(img);
			j.setTeaser(teaser);
			j.setFechaLanzamiento(flan);

			for (Long id : idsPlataforma) {
				Plataforma pl = repoPlat.getOne(id);
				pl.getJuegos().add(j);
				j.getPlataformas().add(pl);
			}

			for (Long id : idsCat) {
				Categoria cat = repoCat.getOne(id);
				cat.getJuegos().add(j);
				j.getCategorias().add(cat);
			}
			repoJuego.save(j);
			
			Foro f1 = new Foro("Fan Art");
			f1.setJuego(j);
			j.getForo().add(f1);
			Foro f2 = new Foro("Debug");
			f2.setJuego(j);
			j.getForo().add(f2);
			Foro f3 = new Foro("Memes");
			f3.setJuego(j);
			j.getForo().add(f3);
			Foro f4 = new Foro("Misc");
			f4.setJuego(j);
			j.getForo().add(f4);
			
			repoForo.save(f1);
			repoForo.save(f2);
			repoForo.save(f3);
			repoForo.save(f4);
			
			
			
		} catch (Exception e) {
			//PRG.error("Juego " + nombre + " duplicado", "/juego/c");
			PRG.error(e.getMessage());
		}
		PRG.info("Juego " + nombre + " creado correctamente", "/juego/r");

	}

	@GetMapping("u")
	public String ueGet(ModelMap m, @RequestParam("id") Long id, HttpSession s) throws DangerException {

		m.put("juego", repoJuego.getOne(id));
		m.put("categorias", repoCat.findAll());
		m.put("plataformas", repoPlat.findAll());
		m.put("view", "/juego/U");
		return "/_t/frame";
	}

	@PostMapping("u")
	public void uPost(@RequestParam("id") Long id, @RequestParam("nombre") String nombre,
			@RequestParam("precio") Double precio, @RequestParam("stock") Integer stock,
			@RequestParam("desarrolladora") String desarrolladora,
			@RequestParam("img") String img, @RequestParam("teaser") String teaser,
			@RequestParam(value = "idPlataforma[]") List<Long> idsPlataforma,
			@RequestParam(value = "idCat[]") List<Long> idsCat,
			@RequestParam("flan") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate flan, HttpSession s)
			throws DangerException, InfoException {
		try {
			Juego j = repoJuego.getOne(id);
			j.setNombre(nombre);
			j.setPrecio(precio);
			j.setStock(stock);
			j.setDesarrolladora(desarrolladora);			
			j.setImg(img);
			j.setTeaser(teaser);
			j.setFechaLanzamiento(flan);
			

			List<Plataforma> plataformasNew = new ArrayList<Plataforma>();
			for (Long idPl : idsPlataforma) {
				Plataforma pl = repoPlat.getOne(idPl);
				pl.getJuegos().add(j);
				plataformasNew.add(pl);
			}
			j.setPlataformas(plataformasNew);

			List<Categoria> categoriasNew = new ArrayList<Categoria>();
			for (Long idCat : idsCat) {
				Categoria cat = repoCat.getOne(idCat);
				cat.getJuegos().add(j);
				categoriasNew.add(cat);
			}
			j.setCategorias(categoriasNew);

			repoJuego.save(j);
		} catch (Exception e) {
			PRG.error("Error al actualizar " + nombre , "/juego/c");
		}
		PRG.info("Juego " + nombre + " actualizado correctamente", "/juego/r");

	}
	
	@PostMapping("d")
	public void dPost(@RequestParam("id") Long id, ModelMap m, HttpSession s) throws DangerException, InfoException {

		
		try {
			repoJuego.deleteById(id);
		} catch (Exception e) {
			PRG.error("Error al borrar el Juego", "/juego/r");
		}

		PRG.info("Juego borrado correctamente", "/juego/r");

	}

}
