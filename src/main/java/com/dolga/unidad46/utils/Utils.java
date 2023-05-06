package com.dolga.unidad46.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {

	public static String formatDate(LocalDateTime time) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		return time.format(formatter);
	}

	public static LocalDateTime parseDate(String dateString) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		return LocalDateTime.parse(dateString, formatter);
	}
}
