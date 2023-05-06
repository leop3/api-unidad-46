package com.dolga.unidad46.services;

import java.util.List;
import java.util.Optional;

import com.dolga.unidad46.dtos.InternoImeiResponse;
import com.dolga.unidad46.dtos.entities.InternoDto;
import com.dolga.unidad46.entities.Interno;

public interface IInternoService {

	public List<InternoDto> getInternos(int numeroPagina, int tamanioPagina, InternoDto interno);
	
	public Optional<Interno> getInterno(Long id);
	
	public void nuevoInterno(InternoDto nuevoInterno);

	public List<InternoImeiResponse> getInternosByImei(String imei);
}
