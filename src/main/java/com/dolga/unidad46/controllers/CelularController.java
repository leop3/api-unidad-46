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

@RestController
@CrossOrigin
@RequestMapping("/unidad/46/celular")
public class CelularController {

	@Autowired
	ICelularService service;

	@PostMapping("/activos")
	public ResponseEntity<List<CelularDto>> getCelularesActivos() {
		var activos = service.getCelularesActivos();
		return ResponseEntity.ok(activos.isPresent() ? activos.get() : Collections.emptyList());
	}

	@GetMapping("/activos/total")
	public ResponseEntity<Integer> getCantidadTotalDeCelularesActivos() {
		var optActivos = service.getCantidadCelularesActivos();
		return ResponseEntity.ok(optActivos.isPresent() ? optActivos.get() : 0);
	}

	@PostMapping("/{idInterno}")
	public ResponseEntity<List<CelularDto>> getCelularesPorInterno(@PathVariable String idInterno, @RequestBody FiltrosCelularDto filtros) {
		return ResponseEntity.of(service.getCelularPorInterno(Long.valueOf(idInterno),filtros));
	}

	@PutMapping
	public ResponseEntity<String> nuevoCelular(@RequestBody CelularDto nuevoCelular) {
		service.nuevoCelularParaInterno(nuevoCelular);
		return ResponseEntity.ok("OK");
	}

	@PostMapping("/discontinuar/{idCelular}")
	public ResponseEntity<Boolean> discontinuarCelularPorId(@PathVariable Long idCelular) {
		return ResponseEntity.ok(service.discontinuarCelular(idCelular));
	}

}
