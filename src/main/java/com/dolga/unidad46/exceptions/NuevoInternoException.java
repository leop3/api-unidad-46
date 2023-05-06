package com.dolga.unidad46.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NuevoInternoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final String estado = "ERROR";

	private String mensaje;

	public NuevoInternoException(String mensaje) {
		this.mensaje = mensaje;
	}
}
