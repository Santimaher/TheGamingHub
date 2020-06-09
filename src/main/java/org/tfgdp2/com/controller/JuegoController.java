package org.tfgdp2.com.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.tfgdp2.com.domain.Categoria_Juego;
import org.tfgdp2.com.domain.Foro;
import org.tfgdp2.com.domain.Juego;
import org.tfgdp2.com.domain.Plataforma;
import org.tfgdp2.com.exception.DangerException;
import org.tfgdp2.com.exception.InfoException;
import org.tfgdp2.com.helper.H;
import org.tfgdp2.com.helper.PRG;
import org.tfgdp2.com.repository.Categoria_JuegoRepository;
import org.tfgdp2.com.repository.ForoRepository;
import org.tfgdp2.com.repository.JuegoRepository;
import org.tfgdp2.com.repository.PlataformaRepository;

@Controller
@RequestMapping(value = "/juego")
public class JuegoController {
	@Autowired
	private JuegoRepository repoJuego;
	@Autowired
	private PlataformaRepository repoPlat;
	@Autowired
	private ForoRepository repoForo;
	@Autowired
	private Categoria_JuegoRepository repoCategoriaJ;

	@GetMapping(value = "r/{pageid}")
	public String read(ModelMap m, @PathVariable int pageid,
			@RequestParam(value = "filtro", required = false) String filtro,
			@RequestParam(value = "tipo", required = false) String tipo, HttpSession s) {
		filtro = (filtro == null) ? "" : filtro;
		tipo = (tipo == null) ? "normal" : tipo;
		int principio = 0;
		int fin = 0;
		Long pl = 0L;
		Long fl = 0L;
		if (pageid == 1) {
			principio = 1;
			fin = 10;
			pl = (long) principio;
			fl = (long) fin;
		} else {
			principio = (pageid * 10) - 9;
			fin = principio + 9;
			pl = (long) principio;
			fl = (long) fin;
		}
		int aguja = pageid;
		ArrayList<Integer> dosAntes = new ArrayList<>();
		ArrayList<Integer> dosDespues = new ArrayList<>();
		if (aguja == 1) {
			for (int i = 2; i < 6; i++) {
				dosDespues.add(i);
			}

		} else if (aguja == 2) {
			dosAntes.add(1);
			for (int i = 3; i < 6; i++) {

				dosDespues.add(i);
			}
		} else {
			for (int i = 1; i < 3; i++) {

				dosDespues.add(aguja + i);
			}
			for (int i = 3; i > 1; i--) {
				dosAntes.add(aguja - i);
			}
		}

		List<Juego> juegos = repoJuego.findByIdBetween(pl, fl);
		switch (tipo) {
		case "Nombre":
			m.put("juegos", repoJuego.findByNombreStartsWithIgnoreCase(filtro));
			break;
		case "Plataforma":
			m.put("juegos", repoJuego.findByPlataformasNombreStartsWithIgnoreCase(filtro));
			break;
		case "normal":
			m.put("juegos", juegos);
			break;
		}
		s.setAttribute("pageid", pageid);
		m.put("pageid", pageid);
		m.put("view", "juego/R");
		m.put("filtro", filtro);
		m.put("antes", dosAntes);
		m.put("durante", aguja);
		m.put("despues", dosDespues);
		m.put("tipo", tipo);
		return "_t/frame";
	}

	@GetMapping("c")
	public String cGet(ModelMap m, HttpSession s) throws DangerException {
		H.isRolOK("admin", s);
		m.put("categorias", repoCategoriaJ.findAll());
		m.put("view", "juego/C");
		return "_t/frame";
	}

	@PostMapping("c")
	public void cPost(@RequestParam("nombre") String nombre, @RequestParam("desarrolladora") String desarrolladora,
			@RequestParam("imgJ") MultipartFile img, @RequestParam("teaser") String teaser,
			@RequestParam(value = "idPlataforma[]") List<Long> idsPlataforma,
			@RequestParam(value = "idCat[]") List<Long> idsCat,
			@RequestParam("flan") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate flan, HttpSession s)
			throws DangerException, InfoException {

		try {
			s.getAttribute("pageid");
			H.isRolOK("admin", s);
			Juego j = new Juego();
			j.setNombre(nombre);
			j.setDesarrolladora(desarrolladora);
			j.setImg(H.blobCreator(img));
			j.setTeaser(teaser);
			j.setFechaLanzamiento(flan);

			for (Long id : idsPlataforma) {
				Plataforma pl = repoPlat.getOne(id);
				pl.getJuegos().add(j);
				j.getPlataformas().add(pl);
			}

			for (Long id : idsCat) {
				Categoria_Juego cat = repoCategoriaJ.getOne(id);
				cat.getJuegos().add(j);
				j.getPertenece().add(cat);
			}
			repoJuego.save(j);

			ArrayList<String> tipos = new ArrayList<>(Arrays.asList("Fan Art", "Debug", "Memes", "Miscelaneo"));
			for (String tipo : tipos) {
				Foro f = new Foro(tipo);
				f.setJuego(j);
				j.getForo().add(f);
				repoForo.save(f);
			}

		} catch (Exception e) {
			PRG.error("Juego " + nombre + " duplicado", "juego/c");

		}
		PRG.info("Juego " + nombre + " creado correctamente", "juego/r/1");

	}

	@GetMapping("u")
	public String ueGet(ModelMap m, @RequestParam("id") Long id, HttpSession s) throws DangerException {
		H.isRolOK("admin", s);
		Juego juego = repoJuego.getOne(id);
		HashSet<Plataforma> consolas = new HashSet<>();
		Collection<Plataforma> colCon = juego.getPlataformas();
		for (Plataforma plataforma : colCon) {
			consolas.add(plataforma);
		}
		m.put("juego", juego);
		m.put("plataformas", consolas);
		m.put("categorias", repoCategoriaJ.findAll());
		m.put("view", "juego/U");
		return "_t/frame";
	}

	@PostMapping("u")
	public void uPost(@RequestParam("id") Long id, @RequestParam("nombre") String nombre,
			@RequestParam("desarrolladora") String desarrolladora,
			@RequestParam(value = "imgJ", required = false) MultipartFile img,
			@RequestParam(value = "imgPre", required = false) String imgPre, @RequestParam("teaser") String teaser,
			@RequestParam(value = "idPlataforma[]") List<Long> idsPlataforma,
			@RequestParam(value = "idCat[]") List<Long> idsCat,
			@RequestParam("flan") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate flan, HttpSession s)
			throws DangerException, InfoException {
		try {
			H.isRolOK("admin", s);
			Juego j = repoJuego.getOne(id);
			j.setNombre(nombre);
			j.setDesarrolladora(desarrolladora);
			if (id <= 181) {
				j.setImgPre(imgPre);
			} else {
				j.setImg(H.blobCreator(img));
			}

			j.setTeaser(teaser);
			j.setFechaLanzamiento(flan);

			List<Plataforma> plataformasNew = new ArrayList<Plataforma>();
			for (Long idPl : idsPlataforma) {
				Plataforma pl = repoPlat.getOne(idPl);
				pl.getJuegos().add(j);
				plataformasNew.add(pl);
			}
			j.setPlataformas(plataformasNew);

			List<Categoria_Juego> categoriasNew = new ArrayList<Categoria_Juego>();
			for (Long idCat : idsCat) {
				Categoria_Juego cat = repoCategoriaJ.getOne(idCat);
				cat.getJuegos().add(j);
				categoriasNew.add(cat);
			}
			j.setPertenece(categoriasNew);

			repoJuego.save(j);
		} catch (Exception e) {
			PRG.error("Error al actualizar " + nombre, "juego/c");
		}
		PRG.info("Juego " + nombre + " actualizado correctamente", "juego/r/" + s.getAttribute("pageid"));

	}

	@PostMapping("d")
	public void dPost(@RequestParam("id") Long id, ModelMap m, HttpSession s) throws DangerException, InfoException {
		try {
			H.isRolOK("admin", s);
			List<Foro> forosAsociados = repoForo.findAllByJuego_id(id);
			for (Foro foro : forosAsociados) {
				repoForo.delete(foro);
			}
			repoJuego.deleteById(id);
		} catch (Exception e) {
			PRG.error("Error al borrar el Juego" + e.getMessage(), "juego/r/1");
		}

		PRG.info("Juego borrado correctamente", "juego/r/" + s.getAttribute("pageid"));

	}

}
