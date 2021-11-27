package com.fatec.backjava.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.backjava.domain.Usuario;
import com.fatec.backjava.dto.UsuarioDTO;
import com.fatec.backjava.dto.UsuarioDedupeDTO;
import com.fatec.backjava.dto.UsuarioKmeansDTO;
import com.fatec.backjava.dto.UsuarioUnicoDTO;
import com.fatec.backjava.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario buscarPorId(Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		return usuario.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado!", null));
	}
	
	public Usuario inserir(UsuarioDTO usuarioDTO) {

		Usuario usuario = usuarioDTO.toEntityInsert();
		
		usuarioRepository.save(usuario);
		return usuario;
	}
	
	public List<Usuario> buscarTodos() {
		return usuarioRepository.findAll();
	}
	
	public List<UsuarioUnicoDTO> buscarUsuarioUnicoDTO() {
		return usuarioRepository.buscarUsuarioUnicoDTO();
	}

	public List<UsuarioKmeansDTO> buscarListaUsuarioKmeansDTO() {
		return usuarioRepository.buscarListaUsuarioKmeansDTO();
	}
	
	public List<UsuarioDedupeDTO> buscarListaUsuarioDedupeDTO() {
		return usuarioRepository.buscarListaUsuarioDedupeDTO();
	}
}
