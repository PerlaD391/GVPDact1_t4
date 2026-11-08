package mx.culturavibe.act1.controlador;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import mx.culturavibe.act1.modelo.Evento;
import mx.culturavibe.act1.servicio.EventoService;

/**
 * Controlador 2: cartelera de eventos.
 * Replica el catálogo de la app de React (que en vivo se alimenta de
 * Ticketmaster); aquí los datos son simulados, como pide la actividad.
 */
@RestController
@RequestMapping("/api/eventos")
public class EventoController {

	private final EventoService eventoService;

	public EventoController(EventoService eventoService) {
		this.eventoService = eventoService;
	}

	/** Todos los eventos, ordenados por fecha. */
	@GetMapping
	public List<Evento> listar() {
		return eventoService.listar();
	}

	/** Un evento por su id (ejemplo: /api/eventos/ev-1). */
	@GetMapping("/{id}")
	public Evento porId(@PathVariable String id) {
		return eventoService.buscarPorId(id)
				.orElseThrow(() -> new ResponseStatusException(
						HttpStatus.NOT_FOUND, "No existe un evento con id " + id));
	}

	/** Eventos de un estado (ejemplo: /api/eventos/estado/CDMX). */
	@GetMapping("/estado/{estado}")
	public List<Evento> porEstado(@PathVariable String estado) {
		return eventoService.porEstado(estado);
	}
}
