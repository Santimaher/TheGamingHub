package org.tfgdp2.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.stereotype.Repository;
=======

import org.springframework.stereotype.Repository;
import org.tfgdp2.com.domain.Foro;


>>>>>>> branch 'master' of https://github.com/Santimaher/TheGamingHub.git
import org.tfgdp2.com.domain.Foro;

@Repository
public interface ForoRepository extends JpaRepository<Foro, Long> {

}