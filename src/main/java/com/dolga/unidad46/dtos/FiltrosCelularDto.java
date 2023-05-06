package com.dolga.unidad46.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FiltrosCelularDto {

	private String numero;
	private String compania;
	private String depositante;
	private String dniDepositante;
	private String accesorios;
	private String imei;
	private String marca;
	private String modelo;
	private Long fichaCriminologia;

}
