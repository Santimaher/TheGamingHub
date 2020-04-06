package org.tfgdp2.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tfgdp2.com.domain.Juego;
import org.tfgdp2.com.domain.Nominacion_Juego;

@Repository
public interface NominacionJuegoRepository extends JpaRepository<Nominacion_Juego, Long> {

}
