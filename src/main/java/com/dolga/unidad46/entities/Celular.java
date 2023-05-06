package com.dolga.unidad46.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "celular", schema = "public")
public class Celular {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "numero")
	private String numero;

	@Column(name = "compania")
	private String compania;

	@Column(name = "depositante")
	private String depositante;

	@Column(name = "dni_depositante")
	private String dniDepositante;

	@Column(name = "accesorios")
	private String accesorios;

	@Column(name = "IMEI")
	private String imei;

	@Column(name = "marca")
	private String marca;

	@Column(name = "modelo")
	private String modelo;

	@Column(name = "ficha_criminologia")
	private Long fichaCriminologia;

	@Column(name = "activo")
	private Boolean activo;
}
