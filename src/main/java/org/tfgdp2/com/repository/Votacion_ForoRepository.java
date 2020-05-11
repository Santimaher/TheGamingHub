package org.tfgdp2.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tfgdp2.com.domain.Categoria_Juego;
import org.tfgdp2.com.domain.Juego;
import org.tfgdp2.com.domain.Usuario;
import org.tfgdp2.com.domain.Votacion_Foro;

@Repository
public interface Votacion_ForoRepository extends JpaRepository<Votacion_Foro, Long> {
	public Votacion_Foro getByVotanteIdAndVotadoId(Long idUsuario,Long idEntrada);
}
