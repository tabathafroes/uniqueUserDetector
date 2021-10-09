package com.fatec.backjava.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.backjava.domain.DadosEntropicosUser;
import com.fatec.backjava.services.DadosEntropicosUserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin()
@RestController
@RequestMapping("dados-entropicos")
public class DadosEntropicosUserResource {

	@Autowired
	private DadosEntropicosUserService dadosEntropicosUserService;
	
	@ApiOperation(value="Realiza a busca de todos os dados entropicos")
	@GetMapping("/buscarTodos")
	public ResponseEntity<List<DadosEntropicosUser>> buscarTodos() {
		List<DadosEntropicosUser> usuarios = dadosEntropicosUserService.buscarTodos();
		return ResponseEntity.ok().body(usuarios);
	}
}
