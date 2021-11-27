package com.fatec.backjava.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name="dedupe_cluster")
public class DedupeCluster implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	@Column(name="cluster_id")
	private int cluster;
    @ManyToOne(fetch= FetchType.EAGER)
	@JoinColumn(name="usuario_id", nullable = false)
	private Usuario usuario;
}
