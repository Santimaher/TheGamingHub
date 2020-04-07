package org.tfgdp2.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tfgdp2.com.domain.Participante;

@Repository
public interface ParticipanteRepository extends JpaRepository<Participante,Long>{

	//public List<Participante> findByIsNominadoTrue();
}
