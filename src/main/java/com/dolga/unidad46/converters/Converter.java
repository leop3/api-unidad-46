package com.dolga.unidad46.converters;

import java.util.Objects;

import org.apache.logging.log4j.util.Strings;

import com.dolga.unidad46.dtos.entities.CelularDto;
import com.dolga.unidad46.dtos.entities.InternoDto;
import com.dolga.unidad46.entities.Celular;
import com.dolga.unidad46.entities.Interno;
import com.dolga.unidad46.utils.Utils;

public class Converter {

	public static InternoDto convertToInternoDto(Interno interno) {
		return InternoDto
				.builder()
				.fichaCriminologia(interno.getFichaCriminologia())
				.sector(interno.getSector())
				.pabellon(interno.getPabellon())
				.apellidos(interno.getApellidos())
				.nombres(interno.getNombres())
				.fechaIngresoString(Utils.formatDate(interno.getFechaIngreso()))
				.fechaIngreso(interno.getFechaIngreso())
				.activo(interno.getActivo())
				.build();
	}

	public static Interno convertToInternoEntity(InternoDto interno) {
		return Interno
				.builder()
				.fichaCriminologia(interno.getFichaCriminologia())
				.sector(interno.getSector())
				.pabellon(interno.getPabellon())
				.apellidos(interno.getApellidos())
				.nombres(interno.getNombres())
				.fechaIngreso(interno.getFechaIngreso())
				.activo(interno.getActivo())
				.build();
	}

	public static Celular converToCelularEntity(CelularDto cel) {
		return Celular
				.builder()
				.numero(cel.getNumero())
				.compania(cel.getCompania())
				.depositante(cel.getDepositante())
				.dniDepositante(cel.getDniDepositante())
				.accesorios(cel.getAccesorios())
				.imei(cel.getImei())
				.marca(cel.getMarca())
				.modelo(cel.getModelo())
				.activo(cel.getActivo())
				.fechaIngreso(Utils.parseLocalDate(cel.getFechaIngreso()))
				.fechaEgreso(Strings.isBlank(cel.getFechaEgreso()) ? null : Utils.parseLocalDate(cel.getFechaEgreso()))
				.fichaCriminologia(cel.getFichaCriminologia())
				.build();

	}

	public static CelularDto ConvertToCelularDto(Celular celDto) {
		return CelularDto
				.builder()
				.id(celDto.getId())
				.numero(celDto.getNumero())
				.compania(celDto.getCompania())
				.depositante(celDto.getDepositante())
				.dniDepositante(celDto.getDniDepositante())
				.accesorios(celDto.getAccesorios())
				.imei(celDto.getImei())
				.marca(celDto.getMarca())
				.modelo(celDto.getModelo())
				.fechaIngreso(Utils.formatDate(celDto.getFechaIngreso()))
				.fechaEgreso(Objects.isNull(celDto.getFechaEgreso()) ? null : Utils.formatDate(celDto.getFechaEgreso()))
				.activo(celDto.getActivo())
				.fichaCriminologia(celDto.getFichaCriminologia())
				.build();
	}

}
