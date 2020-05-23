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
import org.springframework.web.multipart.MultipartFile;
import org.tfgdp2.com.domain.Usuario;
import org.tfgdp2.com.exception.DangerException;
import org.tfgdp2.com.exception.InfoException;
import org.tfgdp2.com.helper.H;
import org.tfgdp2.com.helper.PRG;
import org.tfgdp2.com.repository.UsuarioRepository;

@Controller
@RequestMapping(value = "/usuario")
public class UsuarioController {
	@Autowired
	private UsuarioRepository usuarioRepo;

	@GetMapping("c")
	public String crearGet(ModelMap m, HttpSession s) {
		m.put("view", "usuario/c");
		return "_t/frame";
	}

	@PostMapping("c")
	public String crearPost(@RequestParam("img") String img,@RequestParam("nombre") String nombreusuario, @RequestParam("loginname") String log,
			@RequestParam("pass") String pass, @RequestParam("email") String email, HttpSession s)
			throws DangerException {

		try {
			
			Usuario usuario = new Usuario();
			usuario.setNombre(nombreusuario);
			usuario.setImg(img+".png");
			usuario.setLoginname(log);
			usuario.setPassword(pass);
			usuario.setEmail(email);
			usuario.setRol("auth");

			usuarioRepo.save(usuario);

		} catch (Exception e) {
			PRG.error("Error al crear " + nombreusuario, "usuario/r");
		}
		return "redirect:info";
	}

	@GetMapping("r")
	public String read(ModelMap m, HttpSession s) throws DangerException {
		H.isRolOK("auth", s);
		List<Usuario> usuarios = usuarioRepo.findAll();
		m.put("usuarios", usuarios);
		m.put("view", "usuario/r");
		return "_t/frame";
	}

	
	@GetMapping("uUsuario")
	public String uUsuario(ModelMap m, HttpSession s) throws DangerException {
		H.isRolOK("auth", s);
		Usuario usu=(Usuario) s.getAttribute("usuario");
		m.put("usuario", usuarioRepo.getOne(usu.getId()));
		m.put("view", "usuario/uUsuario");
		return "_t/frame";

	}
	@PostMapping("uUsuarioPost")
	public String uUsuarioPost(@RequestParam("nombre") String nombreusuario, @RequestParam("loginname") String log,
			@RequestParam("id") Long id, @RequestParam("email") String email, HttpSession s) throws DangerException {
		
		try {
			H.isRolOK("auth", s);
			Usuario usuario = usuarioRepo.getOne(id);
			usuario.setNombre(nombreusuario);
			usuario.setLoginname(log);
			usuario.setEmail(email);

			usuarioRepo.save(usuario);

		} catch (Exception e) {
			PRG.error("Error al editar " + nombreusuario, "/usuario/r");
		}
		return "redirect:usuario/r";
	}
	@PostMapping("uAdmin")
	public String uAdmin(ModelMap m, @RequestParam("id") Long id, HttpSession s) throws DangerException {
		H.isRolOK("admin", s);
		m.put("usuario", usuarioRepo.getOne(id));
		m.put("view", "usuario/uAdmin");
		return "_t/frame";

	}
	@PostMapping("uAdminPost")
	public String uAdminPost(@RequestParam("nombre") String nombreusuario, @RequestParam("loginname") String log,
			@RequestParam("id") Long id, @RequestParam("email") String email, HttpSession s) throws DangerException {
		
		try {
			H.isRolOK("admin", s);
			Usuario usuario = usuarioRepo.getOne(id);
			usuario.setNombre(nombreusuario);
			usuario.setLoginname(log);
			usuario.setEmail(email);

			usuarioRepo.save(usuario);

		} catch (Exception e) {
			PRG.error("Error al editar " + nombreusuario, "/usuario/r");
		}
		return "redirect:usuario/r";
	}

	@PostMapping("d")
	public void d(@RequestParam("id") Long id, HttpSession s) throws DangerException, InfoException {
		Usuario u = usuarioRepo.getOne(id);

		try {
			H.isRolOK("auth", s);
			usuarioRepo.delete(u);
		} catch (Exception e) {
			PRG.error("Error al borrar " + u.getNombre(), "usuario/r");
		}
		PRG.info("Usuario borrado correctamente", "usuario/r");
	}
	@PostMapping("cambiarRol")
	public String cambiarRol(ModelMap m, @RequestParam("id") Long id, HttpSession s) throws DangerException {
		 H.isRolOK("admin", s);
		m.put("usuario", usuarioRepo.getOne(id));
		m.put("view", "usuario/cambiarRol");
		return "_t/frame";

	}

	@PostMapping("cambiarRolPost")
	public String cambiarRolPost(@RequestParam("rol") String rol,
			@RequestParam("id") Long id, HttpSession s) throws DangerException {
		String nombre="";

		try {
           H.isRolOK("admin", s);
			Usuario usuario = usuarioRepo.getOne(id);
			usuario.setRol(rol);
            nombre=usuario.getNombre();
			usuarioRepo.save(usuario);

		} catch (Exception e) {
			PRG.error("Error al editar el rol de " + nombre, "usuario/r");
		}
		return "redirect:usuario/r";
	}
}
