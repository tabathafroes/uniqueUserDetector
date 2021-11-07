package com.fatec.backjava.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fatec.backjava.domain.Usuario;
import com.fatec.backjava.dto.ListaUsuarioUnicoDTO;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	@Query("select new com.fatec.backjava.dto.ListaUsuarioUnicoDTO(u.id, u.nome, u.email, de.hash, s.score)"
			+ " from Usuario u, ScoreUsuario s, DadosEntropicosUser de"
			+ " where s.score >= '0.5'"
			+ " and u.id = s.usuario.id"
			+ " and u.id = de.usuario.id"
			+ " ORDER BY u.id ASC")
	List<ListaUsuarioUnicoDTO> buscarUsuarioUnicoDTO();
}
