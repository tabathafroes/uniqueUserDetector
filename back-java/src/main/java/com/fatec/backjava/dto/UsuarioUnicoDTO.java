package com.fatec.backjava.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioUnicoDTO {
	private Long id;
	private String nome;
	private String email;
	private String hash;
	private String score;
}
