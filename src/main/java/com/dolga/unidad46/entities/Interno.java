package com.dolga.unidad46.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "interno", schema = "public")
public class Interno {

	@Id
	@Column(name = "ficha_criminologia")
	private Long fichaCriminologia;

	@Column(name = "sector")
	private String sector;

	@Column(name = "pabellon")
	private Integer pabellon;

	@Column(name = "apellidos")
	private String apellidos;

	@Column(name = "nombres")
	private String nombres;

	@Column(name = "fecha_ingreso")
	private LocalDateTime fechaIngreso;
	
	@Column(name = "fecha_egreso")
	private LocalDateTime fechaEgreso;

	@Column(name = "activo")
	private Boolean activo;

}
