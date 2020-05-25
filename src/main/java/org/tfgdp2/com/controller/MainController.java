package org.tfgdp2.com.controller;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.tfgdp2.com.domain.Usuario;
import org.tfgdp2.com.exception.DangerException;
import org.tfgdp2.com.exception.InfoException;
import org.tfgdp2.com.helper.H;
import org.tfgdp2.com.helper.PRG;
import org.tfgdp2.com.repository.Categoria_JuegoRepository;
import org.tfgdp2.com.repository.EntradaForoRepository;
import org.tfgdp2.com.repository.ForoRepository;
import org.tfgdp2.com.repository.GalaRepository;
import org.tfgdp2.com.repository.JuegoRepository;
import org.tfgdp2.com.repository.NominacionJuegoRepository;
import org.tfgdp2.com.repository.NominacionParticipanteRepository;
import org.tfgdp2.com.repository.ParticipanteRepository;
import org.tfgdp2.com.repository.PlataformaRepository;
import org.tfgdp2.com.repository.PremioJuegoRepository;
import org.tfgdp2.com.repository.PremioParticipanteRepository;
import org.tfgdp2.com.repository.UsuarioRepository;

@Controller
public class MainController {
	@Autowired
	private Categoria_JuegoRepository repoCategoriaJ;
	@Autowired
	private EntradaForoRepository repoEntradaF;
	@Autowired
	private ForoRepository repoForo;
	@Autowired
	private GalaRepository repoGala;
	@Autowired
	private JuegoRepository repoJuego;
	@Autowired
	private NominacionJuegoRepository repoNomJ;
	@Autowired
	private NominacionParticipanteRepository repoNomP;
	@Autowired
	private ParticipanteRepository repoParticipante;
	@Autowired
	private PlataformaRepository repoPlataforma;
	@Autowired
	private PremioJuegoRepository repoPremioJ;
	@Autowired
	private PremioParticipanteRepository repoPremioP;
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@GetMapping("/")
	public String principio(ModelMap m) {
		m.put("view", "home.html");
		return "_t/frame";
	}
	@GetMapping("/danger")
	public String danger(ModelMap m) {
		m.put("view", "anonymous/danger.html");
		m.put("script", "PlataformasAJAX");
		return "_t/frame";
	}
	
	@PostMapping("/danger")
	public void subirPost(@RequestParam("imgJ") MultipartFile img, HttpSession s) throws DangerException, InfoException {
		Usuario u = usuarioRepo.getByLoginname("admin");
		try {
			H.subirImagen(u, img);
		} catch (Exception e) {
			PRG.error("Error al subir imagen"+e.getMessage(), "danger");
		
		}
		PRG.info("Imagen subida", "danger");
		
	}
	
	
	@GetMapping("/info")
	public String info(HttpSession s, ModelMap m) {

		String mensaje = s.getAttribute("_mensaje") != null ? (String) s.getAttribute("_mensaje")
				: "Pulsa para volver a home";
		String severity = s.getAttribute("_severity") != null ? (String) s.getAttribute("_severity") : "info";
		String link = s.getAttribute("_link") != null ? (String) s.getAttribute("_link") : "/";
		String idForo = s.getAttribute("_idForo") != null ? (String) s.getAttribute("_idForo") : "";
		String idEntrada = s.getAttribute("_idEntrada") != null ? (String) s.getAttribute("_idEntrada") : "";
		
		
		s.removeAttribute("_mensaje");
		s.removeAttribute("_severity");
		s.removeAttribute("_link");
		s.removeAttribute("_idForo");
		s.removeAttribute("_idEntrada");

		m.put("mensaje", mensaje);
		m.put("severity", severity);
		m.put("link",link);
		m.put("idForo",idForo);
		m.put("idEntrada", idEntrada);


		m.put("view", "_t/info");
		return "_t/frame";
	}
	@GetMapping("/login")
	public String login(ModelMap m,HttpSession s) throws DangerException {
		
		m.put("view", "anonymous/login");
		return "_t/frame";
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
	@GetMapping("/registro")
	public String registro() 
	{
		return "redirect:usuario/c";
		
	}

	@GetMapping("/cambioContrasenia")
	public String cambioContrasenia(ModelMap m,HttpSession s) 
	{
		m.put("usuario",s.getAttribute("usuario"));
		m.put("view", "usuario/cambiarContrasenia");
		return "_t/frame";
		
	}
	@PostMapping("/cambioContrasenia")
	public String cambioContrasenia(ModelMap m,HttpSession s,@RequestParam("id")Long id,@RequestParam("passwordAc")String actual,@RequestParam("passwordNu")String nueva ) throws DangerException 
	{
		Usuario usu=null;
		try {
			
			H.isRolOK("auth", s);
			 usu = usuarioRepo.getOne(id);
			if (!(new BCryptPasswordEncoder()).matches(actual, usu.getPassword())) {
				throw new Exception();
			}
			usu.setPassword(nueva);
			usuarioRepo.save(usu);

		} catch (Exception e) {
			PRG.error("Error al editar la contraseña de " + usu.getNombre() , "usuario/r");
		}
		return "redirect:usuario/r";
	}
		
	

	

	@GetMapping("/init")
	public String initGet(ModelMap m) throws DangerException {
		if (usuarioRepo.getByLoginname("admin") != null) {
			PRG.error("BD no vacía");
		}
		m.put("view", "anonymous/init");
		return "_t/frame";
	}
	
	@PostMapping("/init")
	public String initPost(@RequestParam("password") String password, ModelMap m) throws DangerException {
		if (usuarioRepo.getByLoginname("admin") != null) {
			PRG.error("Operación no válida. BD no vacía");
		}
		BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();
		if (!bpe.matches(password, bpe.encode("admin"))) { // Password harcoded
			PRG.error("Contraseña incorrecta","/init");
		}
		repoCategoriaJ.deleteAll();
		repoEntradaF.deleteAll();
		repoForo.deleteAll();
		repoGala.deleteAll();
		repoJuego.deleteAll();
		repoNomJ.deleteAll();
		repoNomP.deleteAll();
		repoParticipante.deleteAll();
		repoPlataforma.deleteAll();
		repoPremioJ.deleteAll();
		repoPremioP.deleteAll();
		usuarioRepo.deleteAll();
		usuarioRepo.save(new Usuario("admin", "admin",bpe.encode("admin"), "admin"));
		return "redirect:/";
	}

}
