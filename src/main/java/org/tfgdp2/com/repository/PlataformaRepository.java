package org.tfgdp2.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tfgdp2.com.domain.Plataforma;

@Repository
public interface PlataformaRepository extends JpaRepository<Plataforma, Long>{

}
