package com.dolga.unidad46.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dolga.unidad46.converters.Converter;
import com.dolga.unidad46.dtos.FiltrosCelularDto;
import com.dolga.unidad46.dtos.entities.CelularDto;
import com.dolga.unidad46.repositories.CelularRepositorio;

@Service
public class CelularService implements ICelularService {

	@Autowired
	CelularRepositorio repo;

	@Override
	public CelularDto nuevoCelularParaInterno(CelularDto nuevoCelular) {
		var cel = Converter.converToCelularEntity(nuevoCelular);
		cel.setActivo(Boolean.TRUE);
		return Converter.ConvertToCelularDto(repo.saveAndFlush(cel));
	}

	@Override
	public Optional<List<CelularDto>> getCelularPorInterno(Long idInterno, FiltrosCelularDto filtros) {
		var celulares = repo
				.findByFichaCriminologia(
						idInterno,
						filtros.getNumero(),
						filtros.getCompania(),
						filtros.getDepositante(),
						filtros.getDniDepositante(),
						filtros.getAccesorios(),
						filtros.getImei(),
						filtros.getMarca(),
						filtros.getModelo()
						)
				.stream()
				.map(cel -> Converter.ConvertToCelularDto(cel))
				.collect(Collectors.toList());
		return Optional.of(celulares);
	}

	@Override
	public Optional<Integer> getCantidadCelularesActivos() {

		return repo.countByActivo(Boolean.TRUE);
	}

	@Override
	public Optional<List<CelularDto>> getCelularesActivos() {
		var celulares = repo
				.findByActivoOrderByFichaCriminologiaAsc(Boolean.TRUE)
				.stream()
				.map(cel -> Converter.ConvertToCelularDto(cel))
				.collect(Collectors.toList());
		return Optional.of(celulares);
	}

	@Override
	public Boolean discontinuarCelular(Long idCelular) {
		var celular = repo.findById(idCelular).get();
		celular.setActivo(Boolean.FALSE);
		repo.save(celular);
		return Boolean.TRUE;
	}

}
