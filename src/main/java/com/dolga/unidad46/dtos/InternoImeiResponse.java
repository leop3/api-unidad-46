package com.dolga.unidad46.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class InternoImeiResponse {

	String fichaCriminologia;

	String apellidos;

	String nombres;

	Boolean estadoInterno;

	String imei;

	Boolean estadoCelular;

	public InternoImeiResponse(String fichaCriminología, String apellidos, String nombres, Boolean estadoInterno,
			String imei, Boolean estadoCelular) {
		super();
		this.fichaCriminologia = fichaCriminología;
		this.apellidos = apellidos;
		this.nombres = nombres;
		this.estadoInterno = estadoInterno;
		this.imei = imei;
		this.estadoCelular = estadoCelular;
	}

}
