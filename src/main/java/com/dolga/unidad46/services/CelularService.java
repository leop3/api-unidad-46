package com.dolga.unidad46.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dolga.unidad46.converters.Converter;
import com.dolga.unidad46.dtos.FiltrosCelularDto;
import com.dolga.unidad46.dtos.entities.CelularDto;
import com.dolga.unidad46.repositories.CelularRepositorio;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CelularService implements ICelularService {

	private static final String SUCCES_SEARCH = "Busqueda realizada satisfactoriamente.";
	@Autowired
	CelularRepositorio repo;

	@Override
	public CelularDto nuevoCelularParaInterno(CelularDto nuevoCelular) {
		log.info("Iniciando proceso para nuevo celular...");
		log.info("Convirtiendo Dto a Entidad...");
		var cel = Converter.converToCelularEntity(nuevoCelular);
		cel.setActivo(Boolean.TRUE);
		var response = Converter.ConvertToCelularDto(repo.saveAndFlush(cel));
		log.info("Celular creando satisfactoriamente.");
		return response;
	}

	@Override
	public Optional<List<CelularDto>> getCelularPorInterno(Long idInterno, FiltrosCelularDto filtros) {
		log.info("Iniciando busqueda de celulares del interno: " + idInterno);
		var celulares = repo
				.findByFichaCriminologia(idInterno, filtros.getNumero(), filtros.getCompania(),
						filtros.getDepositante(), filtros.getDniDepositante(), filtros.getAccesorios(),
						filtros.getImei(), filtros.getMarca(), filtros.getModelo())
				.stream()
				.map(cel -> Converter.ConvertToCelularDto(cel))
				.collect(Collectors.toList());
		log.info(SUCCES_SEARCH);
		return Optional.of(celulares);
	}

	@Override
	public Optional<Integer> getCantidadCelularesActivos() {

		log.info("Iniciando la busqueda de la cantidad de celulares activos.");
		var cant = repo.countByActivo(Boolean.TRUE);
		log.info(SUCCES_SEARCH);
		return cant;
	}

	@Override
	public Optional<List<CelularDto>> getCelularesActivos() {
		log.info("Iniciando busqueda de celulares activos.");
		var celulares = repo
				.findByActivoOrderByFichaCriminologiaAsc(Boolean.TRUE)
				.stream()
				.map(cel -> Converter.ConvertToCelularDto(cel))
				.collect(Collectors.toList());
		log.info(SUCCES_SEARCH);
		return Optional.of(celulares);
	}

	@Override
	public Boolean discontinuarCelular(Long idCelular) {
		log.info("Iniciando proceso de discontinuado...");
		log.info("Buscando celular ID: " + idCelular);
		var celular = repo.findById(idCelular).get();
		celular.setActivo(Boolean.FALSE);
		celular.setFechaEgreso(LocalDate.now());
		log.info("Discontinuando celular...");
		repo.save(celular);
		log.info("Celular discontinuado.");
		return Boolean.TRUE;
	}

}
