package org.tfgdp2.com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.tfgdp2.com.domain.Categoria_Participante;
import org.tfgdp2.com.domain.Participante;
import org.tfgdp2.com.exception.DangerException;
import org.tfgdp2.com.exception.InfoException;
import org.tfgdp2.com.helper.H;
import org.tfgdp2.com.helper.PRG;
import org.tfgdp2.com.repository.Categoria_ParticipanteRepository;
import org.tfgdp2.com.repository.ParticipanteRepository;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

@Controller
@RequestMapping(value = "/participante")
public class ParticipanteController {

	@Autowired
	private ParticipanteRepository repoParticipante;

	@Autowired
	private Categoria_ParticipanteRepository repoCategoriaP;

	@GetMapping("c")
	public String crearGet(ModelMap m, HttpSession s) throws DangerException {

		try {
			H.isRolOK("admin", s);
			m.put("categorias", repoCategoriaP.findAll());
			m.put("view", "participante/c");

		} catch (Exception e) {
			m.put("view", "home");
		}
		return "_t/frame";
	}

	@PostMapping("c")
	public String crearPost(@RequestParam("nombre") String nombre, @RequestParam("apellido") String apellido,
			@RequestParam("img") MultipartFile imgFile, @RequestParam("bio") String bio,
			 @RequestParam(value = "idCat") Long idCat, HttpSession s)
			throws DangerException {

		try {
			H.isRolOK("admin", s);

			// =============================C

			Participante participante = new Participante(nombre, apellido, bio);

			participante.setImg(H.blobCreator(imgFile));

			// ===================CATEGORIA

			Categoria_Participante cat = repoCategoriaP.getOne(idCat);
			cat.getParticipantes().add(participante);

			participante.setPertenece(cat);

			// ==================================================

			repoParticipante.save(participante);

		} catch (Exception e) {
			PRG.error("Error al crear el participante " + e.getMessage() + "", "/participante/c");
		}

		return "redirect:/participante/r";
	}

	@GetMapping("r")
	public String read(ModelMap m) {
		m.put("participantes", repoParticipante.findAll());
		m.put("view", "participante/r");
		return "_t/frame";
	}

	@PostMapping("d")
	public String delete(@RequestParam("id") Long id, HttpSession s) throws DangerException {

		try {
			H.isRolOK("admin", s);
			Participante p = repoParticipante.getOne(id);
			repoParticipante.delete(p);

		} catch (Exception e) {
			PRG.error("Error al crear el participante", "/participante/r");
		}
		return "redirect:/participante/r";
	}

	@GetMapping("u")
	public String update(ModelMap m, @RequestParam("id") Long id, HttpSession s) throws DangerException {
		H.isRolOK("admin", s);
		m.put("categorias", repoCategoriaP.findAll());
		m.put("participante", repoParticipante.getOne(id));
		m.put("view", "participante/u");
		return "_t/frame";
	}

	@PostMapping("u")
	public void updatePost(@RequestParam("id") Long idParticipante, @RequestParam("nombre") String nombre,
			@RequestParam("apellido") String apellido, @RequestParam("img") MultipartFile imgFile,
			@RequestParam("bio") String bio,  HttpSession s)
			throws DangerException, InfoException {

		try {
			H.isRolOK("admin", s);
			Participante participante = repoParticipante.getOne(idParticipante);

			participante.setNombre(nombre);
			participante.setApellido(apellido);
			participante.setBio(bio);
			participante.setImg(H.blobCreator(imgFile));

			repoParticipante.save(participante);
		} catch (Exception e) {
			PRG.error("Error al actualizar " + " // " + e.getMessage(), "participante/r");
		}
		PRG.info("Participante actualizado correctamente", "/participante/r");
	}

}
