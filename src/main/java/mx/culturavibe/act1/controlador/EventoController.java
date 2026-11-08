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

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

	private final EventoService eventoService;

	public EventoController(EventoService eventoService) {
		this.eventoService = eventoService;
	}

	@GetMapping
	public List<Evento> listar() {
		return eventoService.listar();
	}

	@GetMapping("/{id}")
	public Evento porId(@PathVariable String id) {
		return eventoService.buscarPorId(id)
				.orElseThrow(() -> new ResponseStatusException(
						HttpStatus.NOT_FOUND, "No existe un evento con id " + id));
	}

	@GetMapping("/estado/{estado}")
	public List<Evento> porEstado(@PathVariable String estado) {
		return eventoService.porEstado(estado);
	}
}
