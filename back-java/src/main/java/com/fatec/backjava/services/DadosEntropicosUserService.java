package com.fatec.backjava.services;

import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.backjava.domain.DadosEntropicosUser;
import com.fatec.backjava.dto.DadosEntropicosUserDTO;
import com.fatec.backjava.repositories.DadosEntropicosUserRepository;

@Service
public class DadosEntropicosUserService {
	
	@Autowired
	private DadosEntropicosUserRepository dadosEntropicosUserRepository;

	public DadosEntropicosUser buscarPorId(Long id) {
		Optional<DadosEntropicosUser> dadosEntropicosUser = dadosEntropicosUserRepository.findById(id);
		return dadosEntropicosUser.orElseThrow(() -> new ObjectNotFoundException("Dados entrópicos do usuário não encontrados!", null));
	}
	
	public DadosEntropicosUser inserir(DadosEntropicosUserDTO dadosEntropicosUserDTO) {

		DadosEntropicosUser dadosEntropicosUser = dadosEntropicosUserDTO.toEntityInsert();
		
		dadosEntropicosUserRepository.save(dadosEntropicosUser);
		return dadosEntropicosUser;
	}
}
