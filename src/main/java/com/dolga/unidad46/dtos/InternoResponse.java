package com.dolga.unidad46.dtos;

import java.util.List;

import com.dolga.unidad46.dtos.entities.InternoDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InternoResponse {

	List<InternoDto> internos;
}
