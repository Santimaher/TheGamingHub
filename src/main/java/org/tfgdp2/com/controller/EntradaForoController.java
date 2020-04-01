package org.tfgdp2.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tfgdp2.com.domain.EntradaForo;
import org.tfgdp2.com.exception.DangerException;
import org.tfgdp2.com.exception.InfoException;
import org.tfgdp2.com.helper.PRG;
import org.tfgdp2.com.repository.EntradaForoRepository;

@Controller
@RequestMapping(value= "/entradaForo")
public class EntradaForoController {
	
	@Autowired
	private EntradaForoRepository repoEntrada;
	@GetMapping("r")
	public String leer(@RequestParam("id") Long id, ModelMap m) {
		m.put("view","/entradaForo/r");
		m.put("entradas",repoEntrada.findByPerteneceId(id));
		return "_t/frame";
	}
	@GetMapping("c")
	public String crear(ModelMap m) {
		m.put("view", "/entradaForo/c");
		return "_t/frame";
	}
	@PostMapping("c")
	public String cPost(@RequestParam("comentario") String comentario) throws DangerException{
		try {
			EntradaForo entrada = new EntradaForo();
			entrada.setComentario(comentario);
			repoEntrada.save(entrada);
		}catch(Exception e) {
			PRG.error("Comentario no creado", "/entradaForo/c");
		}	
		return "redirect:/entradaForo/r";
	}
	@GetMapping("u")
	public String update(ModelMap m, @RequestParam("id") Long idEntrada) throws DangerException {
		m.put("entrada", repoEntrada.getOne(idEntrada));
		m.put("view", "/entradaForo/u");
		return "_t/frame";
	}
	
	@PostMapping("u")
	public void uPost(@RequestParam("comentario") String comentario,@RequestParam("id") Long id) throws InfoException, DangerException {
		try {
			EntradaForo entradaU = repoEntrada.getOne(id);
			entradaU.setComentario(comentario);
			repoEntrada.save(entradaU);
		}catch(Exception e) {
			PRG.error("Error al actualizar la entrada del foro", "/entradaForo/r");
		}
		PRG.info("Entrada actualizada correctamente", "/entradaForo/r");
	}
	
	@PostMapping("d")
	public void dPost(@RequestParam("id") Long id,ModelMap m) throws DangerException, InfoException {
		try {
			repoEntrada.delete(repoEntrada.getOne(id));
		}catch(Exception e) {
			PRG.error("Error al borrar la entrada del foro", "/entradaForo/r");
		}
		PRG.info("Entrada del foro borrada correctamente", "/entradaForo/r");
	}

}
