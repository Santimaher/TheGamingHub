package org.tfgdp2.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tfgdp2.com.domain.Nominacion_Juego;

@Repository
public interface NominacionJuegoRepository extends JpaRepository<Nominacion_Juego, Long> {

	public List<Nominacion_Juego> findByPremioId(Long idPremio);

	public Nominacion_Juego getTopByPremioNombrePremioAndPremioTieneIdOrderByCantidadVotosDesc(String nombre, Long id);
	public List<Nominacion_Juego> findByOrderByCantidadVotosDesc();

}
