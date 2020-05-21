package org.tfgdp2.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tfgdp2.com.domain.Votacion_Foro;
import org.tfgdp2.com.repository.EntradaForoRepository;
import org.tfgdp2.com.repository.Votacion_ForoRepository;

@Controller
@RequestMapping(value= "https://thegaminghub.herokuapp.com/votacion_Foro")
public class Votacion_ForoController {
	@Autowired
	private  Votacion_ForoRepository repoVotacion;
	
public boolean havotado (Long idUsuario, Long idEntrada) 
{
	boolean compro=false;
	
	
	return compro;
	}
}
