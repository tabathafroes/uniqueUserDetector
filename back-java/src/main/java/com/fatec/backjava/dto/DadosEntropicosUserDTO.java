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
	//private String ip;
	private String vendor;
	private String vendorFlavors;
	
	public DadosEntropicosUser toEntityInsert() {
		return new DadosEntropicosUser(id, usuario, cookiesEnabled, deviceMemory, hardwareConcurrency, ip,
				languages, localStorage, platform, sessionStorage, timezone, touchSupport, vendor, vendorFlavors);
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
		dadosEntropicosUser.setVendor(this.vendor);
		dadosEntropicosUser.setVendorFlavors(this.vendorFlavors);
		return dadosEntropicosUser;
	}
}
