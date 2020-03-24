package org.tfgdp2.com.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tfgdp2.com.domain.Usuario;
import org.tfgdp2.com.exception.DangerException;
import org.tfgdp2.com.helper.PRG;
import org.tfgdp2.com.repository.UsuarioRepository;

@Controller
@RequestMapping(value = "/usuario")
public class UsuarioController {
	@Autowired
	private UsuarioRepository usuarioRepo;

	@GetMapping("c")
	public String crearGet(ModelMap m, HttpSession s) {
		m.put("view", "/usuario/c");
		return "/_t/frame";
	}

	@PostMapping("c")
	public String crearPost(@RequestParam("nombre") String nombreusuario, @RequestParam("loginname") String log,
			@RequestParam("pass") String pass, @RequestParam("email") String email, HttpSession s)
			throws DangerException {

		try {

			Usuario usuario = new Usuario();
			usuario.setNombre(nombreusuario);
			usuario.setLoginname(log);
			usuario.setPassword(pass);
			usuario.setEmail(email);
			usuario.setRol("auth");

			usuarioRepo.save(usuario);

		} catch (Exception e) {
			PRG.error("Error al crear " + nombreusuario, "/usuario/r");
		}
		return "redirect:/info";
	}

	@GetMapping("r")
	public String read(ModelMap m, HttpSession s) throws DangerException {
		List<Usuario> usuarios = usuarioRepo.findAll();
		m.put("usuarios", usuarios);
		m.put("view", "/usuario/r");
		return "/_t/frame";
	}

	@PostMapping("u")
	public String u(ModelMap m, @RequestParam("id") Long id, HttpSession s) throws DangerException {
		m.put("usuario", usuarioRepo.getOne(id));
		m.put("view", "/usuario/u");
		return "/_t/frame";

	}

	@PostMapping("uPost")
	public String uPost(@RequestParam("nombre") String nombreusuario, @RequestParam("loginname") String log,
			@RequestParam("id") Long id, @RequestParam("email") String email, HttpSession s) throws DangerException {

		try {

			Usuario usuario = usuarioRepo.getOne(id);
			usuario.setNombre(nombreusuario);
			usuario.setLoginname(log);
			usuario.setEmail(email);

			usuarioRepo.save(usuario);

		} catch (Exception e) {
			PRG.error("Error al editar " + nombreusuario, "/usuario/r");
		}
		return "redirect:/usuario/r";
	}

	@PostMapping("d")
	public String d(@RequestParam("id") Long id, HttpSession s) throws DangerException {
		Usuario u = usuarioRepo.getOne(id);

		try {
			usuarioRepo.delete(u);
		} catch (Exception e) {
			PRG.error("Error al borrar " + u.getNombre(), "/persona/r");
		}
		return "redirect:/usuario/r";
	}
}
