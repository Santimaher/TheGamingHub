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
import org.tfgdp2.com.domain.Usuario;
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
	@GetMapping("rPropio")
	public String leerPropio(@RequestParam("id") Long id, ModelMap m,HttpSession s) {
		m.put("view","/entradaForo/rPropio");
		m.put("id",id);
		m.put("idJuego", repoForo.getOne(id).getJuego().getId());
		Usuario u=(Usuario) s.getAttribute("usuario");
		Long idUsuario=u.getId();
		m.put("entradas",repoEntrada.findByPerteneceIdAndEscribeIdOrderByRankingDesc(id,idUsuario));
		return "_t/frame";
	}
	@GetMapping("verRespuestas")
	public String leerResp(@RequestParam("id") Long id,@RequestParam("idEntrada") Long idEntrada, ModelMap m,HttpSession s) {
		m.put("view","/entradaForo/rRespuestas");
		m.put("id",id);
		m.put("idJuego", repoForo.getOne(id).getJuego().getId());
		m.put("MensajeFijado",repoEntrada.getOne(idEntrada));
		m.put("entradas",repoEntrada.findByMensajePadreIdOrderByRankingDesc(idEntrada));
		return "_t/frame";
	}
	@GetMapping("r")
	public String leer(@RequestParam("id") Long id, ModelMap m) {
		m.put("view","/entradaForo/r");
		m.put("id",id);
		m.put("idJuego", repoForo.getOne(id).getJuego().getId());
		m.put("entradas",repoEntrada.findByPerteneceIdAndMensajePadreIdOrderByRankingDesc(id,null));
		return "_t/frame";
	}
	@GetMapping("responder")
	public String responder(@RequestParam("idForo") Long idForo,ModelMap m,@RequestParam("idEntrada") Long idEntrada,HttpSession s) throws DangerException {
		H.isRolOK("auth", s);
		m.put("view", "/entradaForo/responder");
		m.put("idEntrada", idEntrada);
		m.put("idForo", idForo);
		return "_t/frame";
	}
	@PostMapping("responder")
	public void responderPost(@RequestParam("idForo") Long idForo,@RequestParam("idEntrada") Long idEntrada,@RequestParam("comentario") String comentario,ModelMap m,HttpSession s) throws DangerException, InfoException{
		try {
			H.isRolOK("auth", s);
			EntradaForo entrada = new EntradaForo();
			entrada.setComentario(comentario);
			Foro f=repoForo.getOne(idForo);
			entrada.setPertenece(f);
			f.getPertenecen().add(entrada);
			Usuario u=(Usuario) s.getAttribute("usuario");
			u.getEntradas().add(entrada);
			entrada.setEscribe(u);
			EntradaForo e=repoEntrada.getOne(idEntrada);
			e.getRespuestas().add(entrada);
			entrada.setMensajePadre(e);
			repoEntrada.save(entrada);
			repoEntrada.save(e);
			
		}catch(Exception e) {
			PRG.error("Error al crear la entrada  ("+e.getMessage()+")", "/entradaForo/c");
		}	

		PRG.info("Entrada del foro creada correctamente", "/entradaForo/r",idForo);
	}
	@GetMapping("c")
	public String crear(ModelMap m,@RequestParam("id") Long id,HttpSession s) throws DangerException {
		H.isRolOK("auth", s);
		m.put("view", "/entradaForo/c");
		m.put("id", id);
		return "_t/frame";
	}
	@PostMapping("c")
	public void cPost(@RequestParam("id") Long id,@RequestParam("comentario") String comentario,ModelMap m,HttpSession s) throws DangerException, InfoException{
		try {
			H.isRolOK("auth", s);
			EntradaForo entrada = new EntradaForo();
			entrada.setComentario(comentario);
			entrada.setMensajePadre(null);
			Foro f=repoForo.getOne(id);
			entrada.setPertenece(f);
			f.getPertenecen().add(entrada);
			Usuario u=(Usuario) s.getAttribute("usuario");
			u.getEntradas().add(entrada);
			entrada.setEscribe(u);
			repoEntrada.save(entrada);
		}catch(Exception e) {
			PRG.error("Error al crear la entrada  "+e.getMessage(), "/entradaForo/c");
		}	

		PRG.info("Entrada del foro creada correctamente", "/entradaForo/r",id);
	}
	@GetMapping("u")
	public String update(ModelMap m, @RequestParam("id") Long idEntrada,HttpSession s) throws DangerException {
		H.isRolOK("auth", s);
		m.put("entrada", repoEntrada.getOne(idEntrada));
		m.put("view", "/entradaForo/u");
		return "_t/frame";
	}
	@GetMapping("like")
	public void darLike(ModelMap m, @RequestParam("id") Long idEntrada,@RequestParam("idForo") Long id,HttpSession s) throws DangerException, InfoException {
		H.isRolOK("auth", s);
		EntradaForo e=repoEntrada.getOne(idEntrada);
		e.setRanking(e.getRanking()+1);
		repoEntrada.save(e);
		PRG.info("Entrada del foro editada correctamente", "/entradaForo/r",id);
	}
	@GetMapping("dislike")
	public void quitarLike(ModelMap m, @RequestParam("id") Long idEntrada,@RequestParam("idForo") Long id,HttpSession s) throws DangerException, InfoException {
		H.isRolOK("auth", s);
		EntradaForo e=repoEntrada.getOne(idEntrada);
		e.setRanking(e.getRanking()-1);
		repoEntrada.save(e);
		PRG.info("Entrada del foro editada correctamente", "/entradaForo/r",id);
	}
	
	@PostMapping("u")
	public void uPost(@RequestParam("comentario") String comentario,@RequestParam("idEntrada") Long idEntrada,HttpSession s,ModelMap m) throws InfoException, DangerException {
		EntradaForo entradaU=null;
		try {
			H.isRolOK("auth", s);
			entradaU = repoEntrada.getOne(idEntrada);
			entradaU.setComentario(comentario);
			repoEntrada.save(entradaU);
		}catch(Exception e) {
			PRG.error("Error al actualizar la entrada", "/juego/r");
		}
		Long id=entradaU.getPertenece().getId();
		PRG.info("Entrada del foro editada correctamente", "/entradaForo/r",id);
	}
	
	@PostMapping("d")
	public void dPost(@RequestParam("id") Long id,ModelMap m,HttpSession s) throws DangerException, InfoException {
		Long idforo=repoEntrada.getOne(id).getPertenece().getId();
		try {
			
			H.isRolOK("auth", s);
			repoEntrada.delete(repoEntrada.getOne(id));
		}catch(Exception e) {
			PRG.error(e.getMessage(), "/entradaForo/r",idforo);
		}
		PRG.info("Entrada del foro borrada correctamente", "/entradaForo/r",idforo);
	}

}
