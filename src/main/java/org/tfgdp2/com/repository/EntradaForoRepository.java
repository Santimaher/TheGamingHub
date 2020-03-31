package org.tfgdp2.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tfgdp2.com.domain.EntradaForo;

@Repository
public interface EntradaForoRepository extends JpaRepository<EntradaForo, Long>{

}
