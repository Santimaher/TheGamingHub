package org.tfgdp2.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tfgdp2.com.domain.Categoria_Participante;

@Repository
public interface Categoria_ParticipanteRepository extends JpaRepository<Categoria_Participante, Long> {

}
