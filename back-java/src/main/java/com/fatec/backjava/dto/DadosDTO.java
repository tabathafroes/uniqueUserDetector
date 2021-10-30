package com.fatec.backjava.dto;

import com.fatec.backjava.domain.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DadosDTO {

	private String nome;
	private String email;
	private String telefone;
	private String password;
	private Boolean cookiesEnabled;
	private Long deviceMemory;
	private Long hardwareConcurrency;
	private String ip;
	private String languages;
	private Boolean localStorage;
	private String platform;
	private Boolean sessionStorage;
	private String timezone;
	private Boolean touchSupport;
	private String browser;
	private String browserVersion;
	private String gpu;
	private String tempoCadastro;
	
	public DadosEntropicosUserDTO converterParaDadosEntropicos(Usuario usuario) {
		DadosEntropicosUserDTO dadosEntropicosUserDTO = new DadosEntropicosUserDTO();
		dadosEntropicosUserDTO.setUsuario(usuario);
		dadosEntropicosUserDTO.setCookiesEnabled(this.cookiesEnabled);
		dadosEntropicosUserDTO.setDeviceMemory(this.deviceMemory);
		dadosEntropicosUserDTO.setHardwareConcurrency(this.hardwareConcurrency);
		dadosEntropicosUserDTO.setIp(this.ip);
		dadosEntropicosUserDTO.setLanguages(this.languages);
		dadosEntropicosUserDTO.setLocalStorage(this.localStorage);
		dadosEntropicosUserDTO.setPlatform(this.platform);
		dadosEntropicosUserDTO.setSessionStorage(this.sessionStorage);
		dadosEntropicosUserDTO.setTimezone(this.timezone);
		dadosEntropicosUserDTO.setTouchSupport(this.touchSupport);
		dadosEntropicosUserDTO.setBrowser(this.browser);
		dadosEntropicosUserDTO.setBrowserVersion(this.browserVersion);
		dadosEntropicosUserDTO.setGpu(this.gpu);
		dadosEntropicosUserDTO.setTempoCadastro(this.tempoCadastro);
		return dadosEntropicosUserDTO;
	}
	
	public UsuarioDTO converterParaUsuario() {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setNome(this.nome);
		usuarioDTO.setEmail(this.email);
		usuarioDTO.setPassword(this.password);
		usuarioDTO.setTelefone(this.telefone);
		return usuarioDTO;
	}
}
