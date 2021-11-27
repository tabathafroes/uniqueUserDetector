package com.fatec.backjava.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fatec.backjava.domain.Usuario;
import com.fatec.backjava.dto.UsuarioDedupeDTO;
import com.fatec.backjava.dto.UsuarioKmeansDTO;
import com.fatec.backjava.dto.UsuarioUnicoDTO;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	@Query("select new com.fatec.backjava.dto.UsuarioUnicoDTO(u.id, u.nome, u.email, de.hash, s.score)"
			+ " from Usuario u, ScoreUsuario s, DadosEntropicosUser de"
			+ " where s.score >= '0.5'"
			+ " and u.id = s.usuario.id"
			+ " and u.id = de.usuario.id"
			+ " ORDER BY u.id ASC")
	List<UsuarioUnicoDTO> buscarUsuarioUnicoDTO();

	@Query("select new com.fatec.backjava.dto.UsuarioKmeansDTO(u.id, u.nome, u.email, kc.cluster)"
			+ " from Usuario u, KmeansCluster kc"
			+ " where u.id = kc.usuario.id"
			+ " order by kc.cluster asc")
	List<UsuarioKmeansDTO> buscarListaUsuarioKmeansDTO();
	
	@Query("select new com.fatec.backjava.dto.UsuarioDedupeDTO(u.id, u.nome, u.email, dc.cluster)"
			+ " from Usuario u, DedupeCluster dc"
			+ " where u.id = dc.usuario.id"
			+ " order by dc.cluster asc")
	List<UsuarioDedupeDTO> buscarListaUsuarioDedupeDTO();
}
