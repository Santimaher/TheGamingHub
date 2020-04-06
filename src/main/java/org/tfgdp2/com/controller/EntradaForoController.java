package org.tfgdp2.com.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tfgdp2.com.domain.EntradaForo;
import org.tfgdp2.com.domain.Foro;
import org.tfgdp2.com.exception.DangerException;
import org.tfgdp2.com.exception.InfoException;
import org.tfgdp2.com.helper.H;
import org.tfgdp2.com.helper.PRG;
import org.tfgdp2.com.repository.EntradaForoRepository;
import org.tfgdp2.com.repository.ForoRepository;

@Controller
@RequestMapping(value= "/entradaForo")
public class EntradaForoController {
	
	@Autowired
	private EntradaForoRepository repoEntrada;
	@Autowired
	private ForoRepository repoForo;
	@GetMapping("r")
	public String leer(@RequestParam("id") Long id, ModelMap m) {
		m.put("view","/entradaForo/r");
		m.put("id",id);
		m.put("idJuego", repoForo.getOne(id).getJuego().getId());
		m.put("entradas",repoEntrada.findByPerteneceIdOrderByRankingDesc(id));
		return "_t/frame";
	}
	@GetMapping("c")
	public String crear(ModelMap m,@RequestParam("id") Long id,HttpSession s) throws DangerException {
		H.isRolOK("auth", s);
		m.put("view", "/entradaForo/c");
		m.put("id", id);
		return "_t/frame";
	}
	@PostMapping("c")
	public String cPost(@RequestParam("id") Long id,@RequestParam("comentario") String comentario,ModelMap m,HttpSession s) throws DangerException{
		try {
			H.isRolOK("auth", s);
			EntradaForo entrada = new EntradaForo();
			entrada.setComentario(comentario);
			Foro f=repoForo.getOne(id);
			entrada.setPertenece(f);
			f.getPertenecen().add(entrada);
			repoEntrada.save(entrada);
			//TODO enlazar usuario
		}catch(Exception e) {
			PRG.error("Comentario no creado", "/entradaForo/c");
		}	
		m.put("id", id);

		return "/entradaForo/salvoconducto";
	}
	@GetMapping("u")
	public String update(ModelMap m, @RequestParam("id") Long idEntrada,HttpSession s) throws DangerException {
		H.isRolOK("auth", s);
		m.put("entrada", repoEntrada.getOne(idEntrada));
		m.put("view", "/entradaForo/u");
		return "_t/frame";
	}
	@GetMapping("like")
	public String darLike(ModelMap m, @RequestParam("id") Long idEntrada,@RequestParam("idForo") Long id,HttpSession s) throws DangerException {
		H.isRolOK("auth", s);
		EntradaForo e=repoEntrada.getOne(idEntrada);
		e.setRanking(e.getRanking()+1);
		repoEntrada.save(e);
		m.put("id", id);

		return "/entradaForo/salvoconducto";
	}
	@GetMapping("dislike")
	public String quitarLike(ModelMap m, @RequestParam("id") Long idEntrada,@RequestParam("idForo") Long id,HttpSession s) throws DangerException {
		H.isRolOK("auth", s);
		EntradaForo e=repoEntrada.getOne(idEntrada);
		e.setRanking(e.getRanking()-1);
		repoEntrada.save(e);
		m.put("id", id);

		return "/entradaForo/salvoconducto";
	}
	
	@PostMapping("u")
	public void uPost(@RequestParam("comentario") String comentario,@RequestParam("id") Long id,HttpSession s) throws InfoException, DangerException {
		try {
			H.isRolOK("admin", s);
			EntradaForo entradaU = repoEntrada.getOne(id);
			entradaU.setComentario(comentario);
			repoEntrada.save(entradaU);
		}catch(Exception e) {
			PRG.error("Error al actualizar la entrada del foro", "/entradaForo/r");
		}
		PRG.info("Entrada actualizada correctamente", "/entradaForo/r");
	}
	
	@PostMapping("d")
	public void dPost(@RequestParam("id") Long id,ModelMap m,HttpSession s) throws DangerException, InfoException {
		try {
			H.isRolOK("admin", s);
			repoEntrada.delete(repoEntrada.getOne(id));
		}catch(Exception e) {
			PRG.error("Error al borrar la entrada del foro", "/entradaForo/r");
		}
		PRG.info("Entrada del foro borrada correctamente", "/entradaForo/r");
	}

}
