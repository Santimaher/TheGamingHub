package org.tfgdp2.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tfgdp2.com.domain.EntradaForo;

@Repository
public interface EntradaForoRepository extends JpaRepository<EntradaForo, Long>{
	public List <EntradaForo> findByPerteneceIdOrderByRankingDesc(Long id);
	public List <EntradaForo> findByPerteneceIdAndEscribeIdOrderByRankingDesc(Long idForo,Long idUsuario);
	public List <EntradaForo> findByMensajePadreIdOrderByRankingDesc(Long idMensaje);
	public List <EntradaForo> findByPerteneceIdAndMensajePadreIdOrderByRankingDesc(Long id,Long idPadre);
}
