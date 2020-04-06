package org.tfgdp2.com.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Votacion_Juego {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
}
