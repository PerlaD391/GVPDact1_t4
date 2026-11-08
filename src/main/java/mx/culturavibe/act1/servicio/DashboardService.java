package mx.culturavibe.act1.servicio;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;

import mx.culturavibe.act1.modelo.Evento;
import mx.culturavibe.act1.modelo.ProximoEvento;
import mx.culturavibe.act1.modelo.ResumenDashboard;

@Service
public class DashboardService {

	private static final List<String> CATEGORIAS = List.of("Música", "Teatro", "Libros");

	private static final List<String> ARTISTAS = List.of(
			"Orquesta Sinfónica Nacional",
			"Jazz Quartet MX",
			"Compañía Nacional de Teatro",
			"Coro Polifónico de Guadalajara",
			"Ensamble de Cámara del Bajío"
	);

	private final EventoService eventoService;

	public DashboardService(EventoService eventoService) {
		this.eventoService = eventoService;
	}

	public ResumenDashboard resumen() {
		Evento proximo = eventoService.proximo();
		List<String> recordatorios = eventoService.proximosEnDias(7).stream()
				.map(evento -> {
					long dias = ChronoUnit.DAYS.between(LocalDate.now(), evento.fecha());
					String cuando = dias == 0 ? "hoy" : "en " + dias + (dias == 1 ? " día" : " días");
					return "El evento '" + evento.nombre() + "' es " + cuando
							+ " (" + evento.ciudad() + ", " + evento.estado() + ").";
				})
				.toList();
		return new ResumenDashboard(
				eventoService.listar().size(),
				ARTISTAS.size(),
				CATEGORIAS,
				proximo,
				recordatorios
		);
	}

	public ProximoEvento proximoEvento() {
		Evento proximo = eventoService.proximo();
		long dias = ChronoUnit.DAYS.between(LocalDate.now(), proximo.fecha());
		return new ProximoEvento(proximo, Math.max(dias, 0));
	}
}
