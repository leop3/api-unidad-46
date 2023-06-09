package com.dolga.unidad46.controllers;

import java.util.List;
import java.util.Optional;

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

import com.dolga.unidad46.dtos.InternoImeiResponse;
import com.dolga.unidad46.dtos.InternoResponse;
import com.dolga.unidad46.dtos.entities.InternoDto;
import com.dolga.unidad46.services.IInternoService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@RequestMapping("/unidad/46/internos")
@Slf4j
public class InternoController {

	@Autowired
	IInternoService service;

	@PostMapping("/{page}-{quantity}")
	public ResponseEntity<InternoResponse> getInternos(@PathVariable int page, @PathVariable int quantity,
			@RequestBody InternoDto interno) {
		log.info("Se busca la pagina de internos numero " + page);
		var internos = service.getInternos(page - 1, quantity, interno);
		var response = InternoResponse.builder().internos(internos).build();
		return ResponseEntity.of(Optional.of(response));
	}

	@PutMapping
	public ResponseEntity<String> nuevoInterno(@RequestBody InternoDto nuevoInterno) {

		log.info("Iniciando proceso de creacion de nuevo interno.");
		service.nuevoInterno(nuevoInterno);

		return ResponseEntity.ok("OK");
	}

	@GetMapping("/imei/{imei}")
	public ResponseEntity<List<InternoImeiResponse>> getInternosByImei(@PathVariable String imei) {

		log.info("Iniciando proceso de busqueda de interno por Imei de celular.");
		return ResponseEntity.ok(service.getInternosByImei(imei));
	}
}
