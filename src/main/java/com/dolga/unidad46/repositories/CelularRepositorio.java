package com.dolga.unidad46.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dolga.unidad46.entities.Celular;

@Repository
public interface CelularRepositorio extends JpaRepository<Celular, Long> {

	@Query(nativeQuery = true, value = "select * from celular c " + "where "
			+ "	(ficha_criminologia = :fichaCriminologia) "
			+ "	and (numero ilike '%' || :numero || '%' or :numero = '') "
			+ "	and (compania ilike '%' || :compania || '%' or  :compania = '') "
			+ "	and (depositante  ilike '%' || :depositante || '%' or :depositante = '') "
			+ "	and (dni_depositante  ilike '%' || :dniDepositante  or :dniDepositante || '%' = '') "
			+ "	and (accesorios ilike '%' || :accesorios || '%' or :accesorios = '') "
			+ "	and (imei ilike '%' || :imei || '%' or :imei = '') "
			+ "	and (marca ilike '%' || :marca || '%' or :marca = '') "
			+ "	and (modelo ilike '%' || :modelo || '%' or :modelo = '')")
	public List<Celular> findByFichaCriminologia(
			@Param("fichaCriminologia") Long fichaCriminologia,
			@Param("numero") String numero,
			@Param("compania") String compania,
			@Param("depositante") String depositante,
			@Param("dniDepositante") String dniDepositante,
			@Param("accesorios") String accesorios,
			@Param("imei") String imei,
			@Param("marca") String marca,
			@Param("modelo") String modelo
			);

	public Optional<Integer> countByActivo(Boolean activo);

	public List<Celular> findByActivoOrderByFichaCriminologiaAsc(Boolean activo);
}
