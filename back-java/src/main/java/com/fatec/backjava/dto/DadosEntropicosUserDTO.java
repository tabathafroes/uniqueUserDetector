package com.fatec.backjava.dto;

import com.fatec.backjava.domain.DadosEntropicosUser;
import com.fatec.backjava.domain.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DadosEntropicosUserDTO {

	private Long id;
	private Usuario usuario;
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
	private String hash;
	
	public DadosEntropicosUser toEntityInsert() {
		return new DadosEntropicosUser(id, usuario, cookiesEnabled, deviceMemory, hardwareConcurrency, ip,
				languages, localStorage, platform, sessionStorage, timezone, touchSupport, browser, browserVersion, 
				gpu, tempoCadastro, hash);
	}
	
	public DadosEntropicosUser toEntityUpdate(DadosEntropicosUser dadosEntropicosUser) {
		dadosEntropicosUser.setUsuario(this.usuario);
		dadosEntropicosUser.setCookiesEnabled(this.cookiesEnabled);
		dadosEntropicosUser.setDeviceMemory(this.deviceMemory);
		dadosEntropicosUser.setHardwareConcurrency(this.hardwareConcurrency);
		dadosEntropicosUser.setIp(this.ip);
		dadosEntropicosUser.setLanguages(this.languages);
		dadosEntropicosUser.setLocalStorage(this.localStorage);
		dadosEntropicosUser.setPlatform(this.platform);
		dadosEntropicosUser.setSessionStorage(this.sessionStorage);
		dadosEntropicosUser.setTimezone(this.timezone);
		dadosEntropicosUser.setTouchSupport(this.touchSupport);
		dadosEntropicosUser.setBrowser(this.browser);
		dadosEntropicosUser.setBrowserVersion(this.browserVersion);
		dadosEntropicosUser.setGpu(this.gpu);
		dadosEntropicosUser.setTempoCadastro(this.tempoCadastro);
		return dadosEntropicosUser;
	}
}
