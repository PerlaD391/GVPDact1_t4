package mx.culturavibe.act1.modelo;

import java.util.List;

public record ResumenDashboard(
		int totalEventos,
		int totalArtistas,
		List<String> categorias,
		Evento proximoEvento,
		List<String> recordatorios
) {
}
