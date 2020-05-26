package org.tfgdp2.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tfgdp2.com.domain.Premio_Participante;

@Repository
public interface PremioParticipanteRepository extends JpaRepository<Premio_Participante, Long> {
		public List<Premio_Participante> findByGalaId(Long idGala);
}
