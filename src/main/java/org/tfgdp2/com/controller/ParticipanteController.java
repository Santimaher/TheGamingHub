package org.tfgdp2.com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.tfgdp2.com.domain.Participante;
import org.tfgdp2.com.repository.ParticipanteRepository;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

@Controller
@RequestMapping(value = "/participante")
public class ParticipanteController {
	
	@Autowired
	private ParticipanteRepository repoParticipante;
	
	@GetMapping("c")
	public String crearGet(ModelMap m) {
	try {
		m.put("view", "participante/c");
		
	}catch(Exception e) {
		m.put("view", "/");	
	}
	return "_t/frame";
	}
	
	@PostMapping("c")
	public String crearPost(@RequestParam("nombre") String nombre,@RequestParam("apellido") String apellido,
			@RequestParam("img") MultipartFile imgFile,
			@RequestParam("bio")String bio,
			@RequestParam("teaser") String teaser) throws Exception {
		
		try {
			Participante participante = new Participante(nombre,apellido,bio,teaser);
			participante=repoParticipante.save(participante);
			
			String uploadDir = "/img/upload/";
			String uploadDirRealPath = "";
			String fileName = "_p";
			String fileExtension = "png";
			
			if (imgFile != null && imgFile.getOriginalFilename().split("\\.").length == 2) {
				fileName = "p-" + participante.getId();
				fileExtension = imgFile.getOriginalFilename().split("\\.")[1];
				uploadDirRealPath = "C:\\Users\\alica\\git\\TheGamingHub\\src\\main\\resources\\static\\img\\upload";
				// uploadDirRealPath = sc.getRealPath(uploadDir);
				// uploadDirRealPath ="img/upload";
				File transferFile = new File(uploadDirRealPath + fileName + "." + fileExtension);
				imgFile.transferTo(transferFile);
			}

			String img = uploadDir + fileName + "." + fileExtension;
			participante.setImg(img);
			repoParticipante.save(participante);			
			
		}catch(Exception e) {
			throw e;
		}
		
		return "redirect:/participante/r";
	}
	@GetMapping("r")
	public String read(ModelMap m){
		m.put("participantes",repoParticipante.findAll());
		m.put("view", "participante/r");
		return"_t/frame";
	}
	
	@PostMapping("d")
	public String delete(@RequestParam("id") Long id) {
		try {
			Participante p = repoParticipante.getOne(id);
			repoParticipante.delete(p);
			
		}catch(Exception e) {
			e.getMessage();
		}
		return "redirect:/participante/r";
	}
	
	@GetMapping("u")
	public String update(ModelMap m,@RequestParam("id") Long id) {
		m.put("participante", repoParticipante.getOne(id));
		m.put("view","participante/u");
		return "_t/frame";
	}
	
}
