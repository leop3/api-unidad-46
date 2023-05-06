package com.dolga.unidad46.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InternoErrorResponse {

	private String state;
	private String mensaje;
}
