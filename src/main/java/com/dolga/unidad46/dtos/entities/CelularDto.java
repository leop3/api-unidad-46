package com.dolga.unidad46.dtos.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CelularDto {
	
	private Long id;

	private String numero;

	private String compania;

	private String depositante;

	private String dniDepositante;

	private String accesorios;

	private String imei;

	private String marca;

	private String modelo;
	
	private String fechaIngreso;
	
	private String fechaEgreso;

	private Boolean activo;

	private Long fichaCriminologia;

}
