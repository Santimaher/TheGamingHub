package org.tfgdp2.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tfgdp2.com.domain.Nominacion_Participante;

@Repository
public interface NominacionParticipanteRepository extends JpaRepository<Nominacion_Participante, Long> {

}
