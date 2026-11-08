package mx.culturavibe.act1.modelo;

import java.time.LocalDate;

/**
 * Evento cultural de la cartelera de CulturaVibe.
 * Es un record: Java genera el constructor, los getters, equals/hashCode
 * y toString, y Jackson lo convierte a JSON automáticamente.
 */
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
