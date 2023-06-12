package com.dolga.unidad46.controllers;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dolga.unidad46.dtos.FiltrosCelularDto;
import com.dolga.unidad46.dtos.entities.CelularDto;
import com.dolga.unidad46.services.ICelularService;

import lombok.var;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@RequestMapping("/unidad/46/celular")
@Slf4j
public class CelularController {

	@Autowired
	ICelularService service;

	@PostMapping("/activos")
	public ResponseEntity<List<CelularDto>> getCelularesActivos() {
		log.info("Iniciando proceso de busqueda de celulares activos.");
		var activos = service.getCelularesActivos();
		return ResponseEntity.ok(activos.isPresent() ? activos.get() : Collections.emptyList());
	}

	@GetMapping("/activos/total")
	public ResponseEntity<Integer> getCantidadTotalDeCelularesActivos() {
		log.info("Iniciando proceso de busqueda del total de celulares activos.");
		var optActivos = service.getCantidadCelularesActivos();
		return ResponseEntity.ok(optActivos.isPresent() ? optActivos.get() : 0);
	}

	@PostMapping("/{idInterno}")
	public ResponseEntity<List<CelularDto>> getCelularesPorInterno(@PathVariable String idInterno,
			@RequestBody FiltrosCelularDto filtros) {
		log.info("Buscando la lista de los celulares del interno: " + idInterno);
		return ResponseEntity.of(service.getCelularPorInterno(Long.valueOf(idInterno), filtros));
	}

	@PutMapping
	public ResponseEntity<String> nuevoCelular(@RequestBody CelularDto nuevoCelular) {
		log.info("Iniciando proceso para agregar un celular nuevo para un interno.");
		service.nuevoCelularParaInterno(nuevoCelular);
		return ResponseEntity.ok("OK");
	}

	@PostMapping("/discontinuar/{idCelular}")
	public ResponseEntity<Boolean> discontinuarCelularPorId(@PathVariable Long idCelular) {
		log.info("Se esta por discontinuar el celular de ID: " + idCelular);
		return ResponseEntity.ok(service.discontinuarCelular(idCelular));
	}

}
