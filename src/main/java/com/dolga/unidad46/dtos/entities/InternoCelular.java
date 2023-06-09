package com.dolga.unidad46.dtos.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface InternoCelular {

	Long getFichaCriminologia();

	String getSector();

	Long getPabellon();

	String getApellidos();

	String getNombres();

	LocalDateTime getFechaIngresoInterno();

	String getNumero();

	String getCompania();

	String getDepositante();

	String getDniDepositante();

	String getAccesorios();

	String getImei();

	String getMarca();

	String getModelo();

	LocalDate getFechaIngresoCelular();

}
