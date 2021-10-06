package com.fatec.backjava.dto;

import com.fatec.backjava.domain.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioDTO {
	private Long id;
	private String nome;
	private String email;
	private String password;
	private String telefone;

	public Usuario toEntityInsert() {
		return new Usuario(id, nome, email, password, telefone);
	}
	
	public Usuario toEntityUpdate(Usuario usuario) {
		usuario.setNome(this.nome);
		usuario.setEmail(this.email);
		usuario.setPassword(this.password);
		usuario.setTelefone(this.telefone);
		return usuario;
	}
}
