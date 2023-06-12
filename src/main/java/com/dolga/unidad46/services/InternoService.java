package com.dolga.unidad46.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dolga.unidad46.converters.Converter;
import com.dolga.unidad46.dtos.InternoImeiResponse;
import com.dolga.unidad46.dtos.entities.InternoDto;
import com.dolga.unidad46.entities.Interno;
import com.dolga.unidad46.exceptions.NuevoInternoException;
import com.dolga.unidad46.repositories.InternoRepositorio;
import com.dolga.unidad46.utils.Utils;

import lombok.var;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InternoService implements IInternoService {

	@Autowired
	InternoRepositorio internoRepo;

	@Override
	public List<InternoDto> getInternos(int numeroPagina, int tamanioPagina, InternoDto interno) {

		log.info("Validando parametros de busqueda del interno.");
		validateInternoSearch(interno);

		log.info("Buscando la pagina de los internos.");
		Pageable pageRequest = PageRequest.of(numeroPagina, tamanioPagina);
		var internosB = internoRepo
				.findTodosLosInternos(interno.getFichaCriminologia(), interno.getSector(), interno.getPabellon(),
						interno.getApellidos(), interno.getNombres(), interno.getFechaIngresoString(), pageRequest)
				.getContent()
				.stream()
				.map(inter -> Converter.convertToInternoDto(inter))
				.collect(Collectors.toList());

		return internosB;

	}

	private void validateInternoSearch(InternoDto i) {
		// Ficha
		if (Objects.isNull(i.getFichaCriminologia())) {
			i.setFichaCriminologia(-1L);
		}

		// Fecha
		if (Objects.isNull(i.getFechaIngreso())) {
			i.setFechaIngresoString("");
		} else {
			i.setFechaIngresoString(Utils.formatDate(i.getFechaIngreso()));
		}

		// Pabellon
		if (Objects.isNull(i.getPabellon())) {
			i.setPabellon(-1);
		}

	}

	@Override
	public Optional<Interno> getInterno(Long id) {
		return internoRepo.findById(id);
	}

	@Override
	public void nuevoInterno(InternoDto nuevoInterno) {
		try {
			log.info("Convirtiendo DTO a Entity.");
			var interno = Converter.convertToInternoEntity(nuevoInterno);
			interno.setActivo(Boolean.TRUE);
			log.info("Validando si existe el interno por ficha criminologia.");
			if (internoRepo.existsById(nuevoInterno.getFichaCriminologia())) {
				throw new NuevoInternoException("Ya existe el interno.");
			}
			log.info("Iniciando la creacion del nuevo interno...");
			internoRepo.save(interno);
			log.info("Interno creado satisfactoriamente.");
		} catch (NuevoInternoException e) {
			throw new NuevoInternoException(e.getMensaje());
		} catch (Exception e) {
			throw new NuevoInternoException("Error al guardar el interno.");
		}

	}

	@Override
	public List<InternoImeiResponse> getInternosByImei(String imei) {
		log.info("Comenzando busqueda de internos por IMEI.");
		return internoRepo
				.findInternosByImei(imei)
				.stream()
				.map(r -> InternoImeiResponse
						.builder()
						.fichaCriminologia(r.getfichaCriminología())
						.apellidos(r.getApellidos())
						.nombres(r.getNombres())
						.estadoInterno(r.getEstadoInterno())
						.imei(r.getImei())
						.estadoCelular(r.getEstadoCelular())
						.build())
				.collect(Collectors.toList());
	}

	@Override
	public void discontinuarInterno(String ficha) {
		log.info("Buscando al interno " + ficha + "...");
		var interno = internoRepo
				.findById(Long.valueOf(ficha))
				.orElseThrow(() -> new NuevoInternoException("Interno no encontrado."));
		interno.setActivo(false);
		interno.setFechaEgreso(LocalDateTime.now());
		log.info("Interno dado de baja");

	}

	@Override
	public void eliminarInterno(String ficha) {
		log.info("Eliminando interno...");
		internoRepo.deleteById(Long.valueOf(ficha));
		log.info("Interno " + ficha + " eliminado definitivamente");

	}
}
