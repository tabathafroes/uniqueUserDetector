package com.fatec.backjava.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.backjava.domain.DadosEntropicosUser;
import com.fatec.backjava.dto.DadosEntropicosUserDTO;
import com.fatec.backjava.dto.UsuarioDTO;
import com.fatec.backjava.repositories.DadosEntropicosUserRepository;
import com.fatec.backjava.util.MD5;

@Service
public class DadosEntropicosUserService {
	
	@Autowired
	private DadosEntropicosUserRepository dadosEntropicosUserRepository;

	public DadosEntropicosUser buscarPorId(Long id) {
		Optional<DadosEntropicosUser> dadosEntropicosUser = dadosEntropicosUserRepository.findById(id);
		return dadosEntropicosUser.orElseThrow(() -> new ObjectNotFoundException("Dados entrópicos do usuário não encontrados!", null));
	}
	
	public DadosEntropicosUser inserir(DadosEntropicosUserDTO dadosEntropicosUserDTO, UsuarioDTO usuarioDTO) throws Exception {

		DadosEntropicosUser dadosEntropicosUser = dadosEntropicosUserDTO.toEntityInsert();
		String dadosHash = encriptarDadosHash(dadosEntropicosUser, usuarioDTO);
		dadosEntropicosUser.setHash(dadosHash);
		dadosEntropicosUserRepository.save(dadosEntropicosUser);
		return dadosEntropicosUser;
	}
	
	public String encriptarDadosHash(DadosEntropicosUser dadosEntropicosUser, UsuarioDTO usuarioDTO) throws Exception {
		if(dadosEntropicosUser != null) {
			MD5 md5 = new MD5();
			return md5.encrypt(dadosEntropicosUser, usuarioDTO);
		} 
		return null;
	}
	
	public List<DadosEntropicosUser> buscarTodos() {
		return dadosEntropicosUserRepository.findAll();
	}
}
