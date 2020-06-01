package org.tfgdp2.com.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tfgdp2.com.domain.Categoria_Participante;
import org.tfgdp2.com.exception.DangerException;
import org.tfgdp2.com.exception.InfoException;
import org.tfgdp2.com.helper.H;
import org.tfgdp2.com.helper.PRG;
import org.tfgdp2.com.repository.Categoria_ParticipanteRepository;

@Controller
@RequestMapping(value = "/categoriaParticipante")
public class Categoria_ParticipanteController {

	@Autowired
	private Categoria_ParticipanteRepository repoCategoria_Participante;
	
	@GetMapping("c")
	public String cGet(ModelMap m, HttpSession s) throws DangerException{
			H.isRolOK("admin", s);
			m.put("view", "categoriaParticipante/c");
			return "_t/frame";
 		
	}
	@PostMapping("c")
	public void crearGet(ModelMap m,@RequestParam("nombre") String nombre, HttpSession s) throws DangerException, InfoException {
		try {
			H.isRolOK("admin", s);
			Categoria_Participante cat = new Categoria_Participante();
			cat.setNombre(nombre);
			repoCategoria_Participante.save(cat);
		}catch(Exception e) {
			PRG.error("Categoria "+nombre+" duplicada.", "categoriaParticipante/c");
		}
		PRG.info("Categoría creada correctamente", "categoriaParticipante/r");
	}
	
	@GetMapping("r")
	public String r(ModelMap m) {
		m.put("categorias", repoCategoria_Participante.findAll());
		m.put("view","categoriaParticipante/r");
		return "_t/frame";
	}
	
	@GetMapping("u")
	public String uGet(@RequestParam("id") Long idCategoria, ModelMap m, HttpSession s) throws DangerException {
		H.isRolOK("admin", s);
		m.put("categoria", repoCategoria_Participante.getOne(idCategoria));
		m.put("view", "categoriaParticipante/u");
		return "_t/frame";
	}
	
	@PostMapping("u")
	public void uPost(@RequestParam("nombre") String nombreCategoria, @RequestParam("id") Long idCategoria,
			HttpSession s) throws DangerException, InfoException {

		try {
			H.isRolOK("admin", s);
			Categoria_Participante cat = repoCategoria_Participante.getOne(idCategoria);
			cat.setNombre(nombreCategoria);

			repoCategoria_Participante.save(cat);

		} catch (Exception e) {
			PRG.error("Categoria " + nombreCategoria + " duplicada", "categoriaParticipante/r");
		}
		PRG.info("Categoria " + nombreCategoria + " actualizada correctamente", "categoriaParticipante/r");
	}

	@PostMapping("d")
	public void dPost(@RequestParam("id") Long id, ModelMap m, HttpSession s) throws DangerException, InfoException {

		try {
			H.isRolOK("admin", s);
			repoCategoria_Participante.delete(repoCategoria_Participante.getOne(id));
		} catch (Exception e) {
			PRG.error("Error al borrar la Categoria", "categoriaParticipante/r");
		}

		PRG.info("Categoria borrada correctamente", "categoriaParticipante/r");

	}
	
	
}





