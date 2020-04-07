package org.tfgdp2.com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.tfgdp2.com.domain.Nominacion_Participante;
import org.tfgdp2.com.domain.Participante;
import org.tfgdp2.com.exception.DangerException;
import org.tfgdp2.com.exception.InfoException;
import org.tfgdp2.com.helper.H;
import org.tfgdp2.com.helper.PRG;
import org.tfgdp2.com.repository.NominacionParticipanteRepository;
import org.tfgdp2.com.repository.ParticipanteRepository;

import java.io.File;
import java.util.List;

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
	private NominacionParticipanteRepository repoNominacion;
	
	@GetMapping("c")
	public String crearGet(ModelMap m,HttpSession s) throws DangerException {
		
	try {
		H.isRolOK("admin", s);
		m.put("nominaciones", repoNominacion.findAll());
		m.put("view", "participante/c");
		
	}catch(Exception e) {
		m.put("view", "home");	
	}
	return "_t/frame";
	}
	
	@PostMapping("c")
	public String crearPost(@RequestParam("nombre") String nombre,@RequestParam("apellido") String apellido,
			@RequestParam("img") MultipartFile imgFile,
			@RequestParam("bio")String bio,
			@RequestParam("teaser") String teaser,
			HttpSession s) throws DangerException  {
		
		try {
			H.isRolOK("admin", s);
			Participante participante = new Participante(nombre,apellido,bio,teaser);
			
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
			PRG.error("Error al crear el participante "+e.getStackTrace()+"", "/participante/c");
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
	public String delete(@RequestParam("id") Long id,HttpSession s) throws DangerException {
		
		try {
			H.isRolOK("admin", s);
			Participante p = repoParticipante.getOne(id);
			repoParticipante.delete(p);
			
		}catch(Exception e) {
			PRG.error("Error al crear el participante","/participante/r");
		}
		return "redirect:/participante/r";
	}
	
	@GetMapping("u")
	public String update(ModelMap m,@RequestParam("id") Long id,HttpSession s) throws DangerException {
		H.isRolOK("admin", s);
		m.put("nominaciones", repoNominacion.findAll());
		m.put("participante", repoParticipante.getOne(id));
		m.put("view","participante/u");
		return "_t/frame";
	}
	
	@PostMapping("u")
	public void updatePost(@RequestParam("id") Long idParticipante,
			@RequestParam("nombre") String nombre,
			@RequestParam("apellido") String apellido,
			@RequestParam("img") MultipartFile imgFile,
			@RequestParam("bio")String bio,
			@RequestParam("teaser") String teaser,
			HttpSession s) throws DangerException, InfoException {
		
		try {
			H.isRolOK("admin", s);
				Participante participante = repoParticipante.getOne(idParticipante);
        
        		participante.setNombre(nombre);
        		participante.setApellido(apellido);
        		participante.setBio(bio);
        		participante.setTeaser(teaser);
			
				
				//============IMG
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
			//==============
			
			
			repoParticipante.save(participante);
		}catch(Exception e) {
			PRG.error("Error al actualizar " +" // "+e.getMessage(),"participante/r" );
		}
		PRG.info("Participante actualizado correctamente", "/participante/r");
	}
	
}
