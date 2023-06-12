package com.dolga.unidad46.dtos.entities;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InternoDto {

	private Long fichaCriminologia;

	private String sector;

	private Integer pabellon;

	private String apellidos;

	private String nombres;

	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime fechaIngreso;
	
	private String fechaIngresoString;
	
	private Boolean activo;

}
