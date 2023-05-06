package com.dolga.unidad46.services;

import java.util.List;
import java.util.Optional;

import com.dolga.unidad46.dtos.FiltrosCelularDto;
import com.dolga.unidad46.dtos.entities.CelularDto;

public interface ICelularService {

	public CelularDto nuevoCelularParaInterno(CelularDto nuevoCelular);

	public Optional<List<CelularDto>> getCelularPorInterno(Long idInterno, FiltrosCelularDto filtros);

	public Optional<Integer> getCantidadCelularesActivos();

	public Optional<List<CelularDto>> getCelularesActivos();

	public Boolean discontinuarCelular(Long idCelular);

}
