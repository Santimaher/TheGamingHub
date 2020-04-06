package org.tfgdp2.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tfgdp2.com.repository.GalaRepository;

@Controller
@RequestMapping(value="/gala")
public class GalaController {

	@Autowired GalaRepository repoGala;
	
	@GetMapping("r")
	public String read(ModelMap m) {
		m.put("galas", repoGala.findAll());
		m.put("view", "gala/r");
		return "/_t/frame";
	}
}
