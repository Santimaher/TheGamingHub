package org.tfgdp2.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tfgdp2.com.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

}
