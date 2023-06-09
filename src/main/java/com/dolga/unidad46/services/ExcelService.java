package com.dolga.unidad46.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dolga.unidad46.enums.Uris;
import com.dolga.unidad46.repositories.CelularRepositorio;
import com.dolga.unidad46.utils.Utils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ExcelService implements IExcelService {

	@Autowired
	CelularRepositorio celuRepo;

	@Override
	public void getExcelFile() {

		var internosCelulares = celuRepo.getInternosConCelularActivo();

		File excel = null;
		var workbook = new HSSFWorkbook();
		var sheet = workbook.createSheet("Internos");
		log.info("Hoja creada.");
		var encabezadosRow = sheet.createRow(0);
		log.info("Fila creada.");
		// ENCABEZADOS
		encabezadosRow.createCell(0).setCellValue("Ficha Criminologia");
		encabezadosRow.createCell(1).setCellValue("Sector");
		encabezadosRow.createCell(2).setCellValue("Pabellon");
		encabezadosRow.createCell(3).setCellValue("Apellidos");
		encabezadosRow.createCell(4).setCellValue("Nombres");
		encabezadosRow.createCell(5).setCellValue("Fecha Ingreso interno");
		encabezadosRow.createCell(6).setCellValue("N° de celular");
		encabezadosRow.createCell(7).setCellValue("Compania");
		encabezadosRow.createCell(8).setCellValue("Depositante");
		encabezadosRow.createCell(9).setCellValue("Dni depositante");
		encabezadosRow.createCell(10).setCellValue("Accesorios");
		encabezadosRow.createCell(11).setCellValue("IMEI");
		encabezadosRow.createCell(12).setCellValue("Marca");
		encabezadosRow.createCell(13).setCellValue("Modelo");
		encabezadosRow.createCell(14).setCellValue("Fecha Ingreso Celular");

		log.info("Encabezados creados.");
		// Llenando los datos.
		var rowCount = new AtomicInteger(1);

		internosCelulares.stream().forEach(ic -> {
			var rowData = sheet.createRow(rowCount.getAndIncrement());

			rowData.createCell(0).setCellValue(ic.getFichaCriminologia());
			rowData.createCell(1).setCellValue(ic.getSector());
			rowData.createCell(2).setCellValue(ic.getPabellon());
			rowData.createCell(3).setCellValue(ic.getApellidos());
			rowData.createCell(4).setCellValue(ic.getNombres());
			rowData.createCell(5).setCellValue(Utils.formatDate(ic.getFechaIngresoInterno()));
			rowData.createCell(6).setCellValue(ic.getNumero());
			rowData.createCell(7).setCellValue(ic.getCompania());
			rowData.createCell(8).setCellValue(ic.getDepositante());
			rowData.createCell(9).setCellValue(ic.getDniDepositante());
			rowData.createCell(10).setCellValue(ic.getAccesorios());
			rowData.createCell(11).setCellValue(ic.getImei());
			rowData.createCell(12).setCellValue(ic.getMarca());
			rowData.createCell(13).setCellValue(ic.getModelo());
			rowData.createCell(14).setCellValue(Utils.formatDate(ic.getFechaIngresoCelular()));
		});

		log.info("Se completaron las filas");

		excel = new File(Uris.NAME_EXCEL.getUri());

		log.info("Se genero el archivo.");

		try {
			var output = new FileOutputStream(excel);
			workbook.write(output);
			log.info("Se completo el archivo Excel.");
		} catch (Exception e) {
			log.info("Se produjo un error al crear el excel.");
		}

		try {
			workbook.close();
		} catch (IOException e) {
			log.info("Se produjo un error al cerrar el excel.");
		}

	}

}
