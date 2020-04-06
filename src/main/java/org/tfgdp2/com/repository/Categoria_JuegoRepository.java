package org.tfgdp2.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tfgdp2.com.domain.Categoria_Juego;

@Repository
public interface Categoria_JuegoRepository extends JpaRepository<Categoria_Juego, Long> {

}
