package com.fatec.backjava.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="dados_entropicos_user")
public class DadosEntropicosUser implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="usuario_id", nullable = false)
	private Usuario usuario;
	@Column(name="cookies_enabled")
	private Boolean cookiesEnabled;
	@Column(name="device_memory")
	private Long deviceMemory;
	@Column(name="hardware_concurrency")
	private Long hardwareConcurrency;
	private String ip;
	private String languages;
	@Column(name="local_storage")
	private Boolean localStorage;
	private String platform;
	@Column(name="session_storage")
	private Boolean sessionStorage;
	private String timezone;
	@Column(name="touch_support")
	private Boolean touchSupport;
	private String vendor;
	@Column(name="vendor_flavors")
	private String vendorFlavors;
	private String hash;
}
