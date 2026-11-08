package mx.culturavibe.act1.servicio;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import mx.culturavibe.act1.modelo.Evento;

/**
 * Catálogo de eventos. Son datos simulados (como pide la actividad):
 * en la app de React estos eventos vienen de Ticketmaster, aquí van fijos
 * en el código para poder probarlos sin llave ni internet.
 */
@Service
public class EventoService {

	private static final List<Evento> EVENTOS = List.of(
			new Evento("ev-1", "Concierto Sinfónico de Gala",
					LocalDate.of(2026, 11, 12), "Palacio de Bellas Artes",
					"Ciudad de México", "CDMX", "$450",
					"https://www.ticketmaster.com.mx"),
			new Evento("ev-2", "Festival Internacional de Cine",
					LocalDate.of(2026, 11, 22), "Conjunto Santander",
					"Guadalajara", "JAL", "$180",
					"https://www.ticketmaster.com.mx"),
			new Evento("ev-3", "Noche de Jazz en el Barrio Antiguo",
					LocalDate.of(2026, 11, 25), "Barrio Antiguo",
					"Monterrey", "NL", "$250",
					"https://www.ticketmaster.com.mx"),
			new Evento("ev-4", "Exposición de Arte Contemporáneo",
					LocalDate.of(2026, 11, 28), "Museo Amparo",
					"Puebla", "PUE", "Gratis",
					"https://www.ticketmaster.com.mx"),
			new Evento("ev-5", "Feria del Libro",
					LocalDate.of(2026, 12, 2), "Centro Cultural",
					"Cuernavaca", "MOR", "Gratis",
					"https://www.ticketmaster.com.mx"),
			new Evento("ev-6", "Festival de Teatro Clásico",
					LocalDate.of(2026, 12, 5), "Teatro Principal",
					"Acapulco", "GRO", "$320",
					"https://www.ticketmaster.com.mx")
	);

	/** Todos los eventos, ordenados por fecha. */
	public List<Evento> listar() {
		return EVENTOS.stream()
				.sorted(Comparator.comparing(Evento::fecha))
				.toList();
	}

	/** Un evento por su id, si existe. */
	public Optional<Evento> buscarPorId(String id) {
		return EVENTOS.stream()
				.filter(evento -> evento.id().equalsIgnoreCase(id))
				.findFirst();
	}

	/** Eventos de un estado (código como CDMX, JAL, NL, PUE, MOR o GRO). */
	public List<Evento> porEstado(String estado) {
		return listar().stream()
				.filter(evento -> evento.estado().equalsIgnoreCase(estado))
				.toList();
	}

	/** El próximo evento: el más cercano que aún no ha pasado. */
	public Evento proximo() {
		LocalDate hoy = LocalDate.now();
		return listar().stream()
				.filter(evento -> !evento.fecha().isBefore(hoy))
				.findFirst()
				.orElse(listar().get(0));
	}

	/** Eventos que ocurren dentro de los próximos días (para recordatorios). */
	public List<Evento> proximosEnDias(int dias) {
		LocalDate hoy = LocalDate.now();
		LocalDate limite = hoy.plusDays(dias);
		return listar().stream()
				.filter(evento -> !evento.fecha().isBefore(hoy)
						&& !evento.fecha().isAfter(limite))
				.toList();
	}
}
