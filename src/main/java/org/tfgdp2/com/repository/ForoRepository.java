package org.tfgdp2.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import org.tfgdp2.com.domain.Foro;

@Repository
public interface ForoRepository extends JpaRepository<Foro, Long> {

}
