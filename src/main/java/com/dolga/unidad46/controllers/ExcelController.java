package com.dolga.unidad46.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dolga.unidad46.enums.Uris;
import com.dolga.unidad46.services.IExcelService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@RequestMapping("/unidad/46/excel")
@Slf4j
public class ExcelController {

	@Autowired
	IExcelService service;

	@GetMapping
	public ResponseEntity<InputStreamResource> downloadExcel() throws FileNotFoundException {
		// Ruta al archivo de Excel generado

		log.info("Comienza el armado del archivo excel.");
		service.getExcelFile();

		// Crear un objeto InputStreamResource a partir del archivo
		log.info("Se recupera el archivo creado para enviar.");
		InputStream inputStream = new FileInputStream(Uris.NAME_EXCEL.getUri());
		InputStreamResource resource = new InputStreamResource(inputStream);

		// Configurar las cabeceras de la respuesta HTTP
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ejemplo.xls");
		headers
				.setContentType(
						MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));

		// Devolver la respuesta con el archivo adjunto
		return new ResponseEntity<>(resource, headers, HttpStatus.OK);
	}

}
