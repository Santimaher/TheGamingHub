package org.tfgdp2.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tfgdp2.com.domain.Nominacion_Juego;
import org.tfgdp2.com.domain.Nominacion_Participante;

@Repository
public interface NominacionParticipanteRepository extends JpaRepository<Nominacion_Participante, Long> {

	public List<Nominacion_Participante> findByPremioId(Long idPremio);

	public Nominacion_Participante getTopByPremioNombrePremioAndPremioTieneIdOrderByCantidadVotosDesc(String nombre,Long id);
	public List<Nominacion_Participante> findByOrderByCantidadVotosDesc();
}
