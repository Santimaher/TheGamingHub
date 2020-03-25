package org.tfgdp2.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.tfgdp2.com.repository.NominacionRepository;

@Controller
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
}
