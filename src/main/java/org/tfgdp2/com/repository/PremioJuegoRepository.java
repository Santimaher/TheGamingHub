package org.tfgdp2.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tfgdp2.com.domain.Premio_Juego;

@Repository
public interface PremioJuegoRepository extends JpaRepository<Premio_Juego, Long> {

}