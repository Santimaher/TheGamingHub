package org.tfgdp2.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tfgdp2.com.domain.Juego;

@Repository
public interface JuegoRepository extends JpaRepository<Juego, Long> {

	public Long deleteJuegoById(Long id);

	public List<Juego> findByEstaNominadoTrue();

	public List<Juego> findByNombreStartsWithIgnoreCase(String filtro);

	public List<Juego> findByPlataformasNombreStartsWithIgnoreCase(String filtro);

	public List<Juego> findByIdBetween(Long principio, Long fin);
}
