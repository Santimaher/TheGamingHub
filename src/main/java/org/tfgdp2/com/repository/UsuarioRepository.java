package org.tfgdp2.com.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tfgdp2.com.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
	public Usuario getByLoginnameOrEmail(String lognom,String email);
}
