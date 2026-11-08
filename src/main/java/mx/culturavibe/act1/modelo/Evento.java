package mx.culturavibe.act1.modelo;

import java.time.LocalDate;

public record Evento(
		String id,
		String nombre,
		LocalDate fecha,
		String sede,
		String ciudad,
		String estado,
		String precio,
		String url
) {
}
