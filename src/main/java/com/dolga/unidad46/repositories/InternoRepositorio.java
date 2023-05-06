package com.dolga.unidad46.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dolga.unidad46.dtos.entities.ImeiResultSql;
import com.dolga.unidad46.entities.Interno;

public interface InternoRepositorio extends JpaRepository<Interno, Long> {

	Page<Interno> findAllByOrderByFichaCriminologia(Pageable pageable);

	@Query(value = "select * " + "from " + "	interno i " + "where "
			+ "	(ficha_criminologia = :fichaCriminologia or :fichaCriminologia = -1) "
			+ "	and (sector ilike '%' || :sector || '%' or :sector = '') "
			+ "	and (pabellon = :pabellon or  :pabellon = -1) "
			+ "	and (apellidos ilike '%' || :apellidos  || '%' or :apellidos = '') "
			+ "	and (nombres ilike '%' || :nombres  || '%' or :nombres = '') "
			+ "	and (fecha_ingreso > to_timestamp(:fechaIngresoString,'DD/MM/YYYY HH24:MI:SS') or :fechaIngresoString = '' ) "
			+ "	and activo is true "
			+ " order by ficha_criminologia asc" , nativeQuery = true)
	Page<Interno> findTodosLosInternos(@Param("fichaCriminologia") Long fichaCriminologia,
			@Param("sector") String sector, @Param("pabellon") Integer pabellon, @Param("apellidos") String apellidos,
			@Param("nombres") String nombres, @Param("fechaIngresoString") String fechaIngresoString,
			Pageable pageable);

	@Query(value = "SELECT i.ficha_criminologia as fichaCriminología, i.apellidos , i.nombres, i.activo as estadoInterno , c.imei , c.activo as estadoCelular "
			+ "from interno i inner join celular c on i.ficha_criminologia = c.ficha_criminologia "
			+ "where imei = :imei", nativeQuery = true)
	List<ImeiResultSql> findInternosByImei(@Param("imei") String imei);

}
