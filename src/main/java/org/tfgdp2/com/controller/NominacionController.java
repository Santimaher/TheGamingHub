package org.tfgdp2.com.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tfgdp2.com.domain.Nominacion;
import org.tfgdp2.com.exception.DangerException;
import org.tfgdp2.com.exception.InfoException;
import org.tfgdp2.com.helper.PRG;
import org.tfgdp2.com.repository.NominacionRepository;

@Controller
@RequestMapping("nominacion")
public class NominacionController {
		  @Autowired
		  private NominacionRepository repoNominacion;
		  
		  @GetMapping("c")
		  public String creatGet(ModelMap m) {
			  try {
				  m.put("view", "nominacion/c");
			  }catch(Exception e) {
				  m.put("view", "/");
			  }
		  return "_t/frame";
		  }
		  
		  @PostMapping("c")
		  public void crearPost(@RequestParam("nombre") String nombre) throws InfoException, DangerException {
			  try {
				  repoNominacion.save(new Nominacion(nombre));		  
			  }catch(Exception e) {
				  PRG.error("Nominación duplicada");
			  }
			  PRG.info("Nominación creada correctamente", "/nominacion/r");
		  }
		  
		  @GetMapping("r")
			public String read(ModelMap m) throws DangerException {
				List<Nominacion> n = repoNominacion.findAll();
				m.put("nominaciones", n);

				m.put("view", "/nominacion/r");
				return "/_t/frame";
			}
		  
		  @PostMapping("d")
		  public void delete(@RequestParam("id") Long id) throws DangerException, InfoException {
			  try {
				  Nominacion n = repoNominacion.getOne(id);
				  repoNominacion.delete(n);
			  }catch(Exception e) {
				  PRG.error("Error al eliminar la nominación actual","/nominacion/r");
			  }
			  
			  PRG.info("Nominación borrada correctamente", "/nominacion/r");
		  }
			@GetMapping("u")
			public String updateGet(@RequestParam("id") Long idPais, ModelMap m){
				m.put("nominacion", repoNominacion.getOne(idPais));
				m.put("view", "/nominacion/u");
				return "/_t/frame";
			}
			
			@PostMapping("u")
			public void updatePost(@RequestParam("nombre") String nombre, @RequestParam("id") Long id)
					throws DangerException, InfoException {
				try {
					Nominacion n = repoNominacion.getOne(id);
					n.setNombre(nombre);
					repoNominacion.save(n);
				} catch (Exception e) {
					PRG.error("Nominación duplicada", "/nominacion/r");
				}
				PRG.info("Nominacón actualizada correctamente", "/nominacion/r");
			}
		  
}
