package mx.culturavibe.act1.modelo;

import java.util.List;

/**
 * Resumen del panel principal (la ruta /dashboard de la app de React):
 * los mismos datos que muestran las tarjetas del Dashboard de CulturaVibe.
 */
public record ResumenDashboard(
		int totalEventos,
		int totalArtistas,
		List<String> categorias,
		Evento proximoEvento,
		List<String> recordatorios
) {
}
