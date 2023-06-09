package com.dolga.unidad46;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class Unidad46Application {

	public static void main(String[] args) {
		SpringApplication.run(Unidad46Application.class, args);
		log.info("================APLICACION INICIADA================");
	}

}
