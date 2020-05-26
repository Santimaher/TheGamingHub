package org.tfgdp2.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tfgdp2.com.domain.Gala;

@Repository
public interface GalaRepository extends JpaRepository<Gala, Long> {

	public Long deleteGalaById (Long id);
	
	public Gala findTopByOrderByEdicionDesc();
	public List<Gala> findByOrderByEdicionDesc();
	public Gala getByActivoTrue();
}
