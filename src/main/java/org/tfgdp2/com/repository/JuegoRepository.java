package org.tfgdp2.com.repository;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.tfgdp2.com.domain.Juego;


@Repository
public interface JuegoRepository extends JpaRepository<Juego, Long> {	
	
	public Long deleteJuegoById (Long id);
	public List<Juego> findByEstaNominadoTrue();
    public List<Juego>findByNombreStartsWithIgnoreCase(String filtro);
    public List<Juego>findByPlataformasNombreStartsWithIgnoreCase(String filtro);
    
    @Query(value="select * from juego j where j.id > ?1 and j.id < ?2")
    public List<Juego> findByIdBetweenQuery(int start, int end);
}
