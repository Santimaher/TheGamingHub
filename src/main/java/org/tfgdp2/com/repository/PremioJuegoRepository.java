package org.tfgdp2.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tfgdp2.com.domain.Foro;
import org.tfgdp2.com.domain.Premio_Juego;
import org.tfgdp2.com.domain.Premio_Participante;

@Repository
public interface PremioJuegoRepository extends JpaRepository<Premio_Juego, Long> {
	public List<Premio_Juego> findByTiene_id(Long idGala);
}
