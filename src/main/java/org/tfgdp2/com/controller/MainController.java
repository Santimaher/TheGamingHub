package org.tfgdp2.com.controller;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tfgdp2.com.domain.Usuario;
import org.tfgdp2.com.exception.DangerException;
import org.tfgdp2.com.helper.PRG;
import org.tfgdp2.com.repository.UsuarioRepository;

@Controller
public class MainController {
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@GetMapping("/")
	public String principio(ModelMap m) {
		m.put("view", "home.html");
		
		return "/_t/frame";
	}
	
	@GetMapping("/info")
	public String info(HttpSession s, ModelMap m) {

		String mensaje = s.getAttribute("_mensaje") != null ? (String) s.getAttribute("_mensaje")
				: "Pulsa para volver a home";
		String severity = s.getAttribute("_severity") != null ? (String) s.getAttribute("_severity") : "info";
		String link = s.getAttribute("_link") != null ? (String) s.getAttribute("_link") : "/";

		s.removeAttribute("_mensaje");
		s.removeAttribute("_severity");
		s.removeAttribute("_link");

		m.put("mensaje", mensaje);
		m.put("severity", severity);
		m.put("link", link);

		m.put("view", "/_t/info");
		return "/_t/frame";
	}
	@GetMapping("/login")
	public String login(ModelMap m,HttpSession s) throws DangerException {
		
		m.put("view", "/anonymous/login");
		return "/_t/frame";
	}
	@PostMapping("/login")
	public String login(@RequestParam("loginname") String loginname, @RequestParam("password") String password,
			ModelMap m, HttpSession s) throws DangerException {
		String view = "/";
		try {
			Usuario usu = usuarioRepo.getByLoginnameOrEmail(loginname,loginname);
			if (!(new BCryptPasswordEncoder()).matches(password, usu.getPassword())) {
				throw new Exception();
			}
			s.setAttribute("usuario", usu);
		} catch (Exception e) {
			PRG.error("Usuario o contraseña incorrecta", "/login");
			view = "/info";
		}

		return "redirect:" + view;
	}
	@GetMapping("/logout")
	public String logout(HttpSession s) throws DangerException {
		s.invalidate();
		return "redirect:/";
	}
}
