package mx.culturavibe.act1.controlador;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.culturavibe.act1.modelo.Integrante;

/**
 * Controlador 1: información del proyecto.
 * Sirve para identificar de quién es cada instancia corriendo en el VPS.
 */
@RestController
@RequestMapping("/api/proyecto")
public class ProyectoController {

	/** a) Nombre del proyecto e identificación de la instancia. */
	@GetMapping
	public Map<String, String> proyecto() {
		return Map.of(
				"proyecto", "CulturaVibe",
				"iniciales", "GVPD",
				"integrante", "Perla Danae Gallardo Vasquez",
				"equipo", "13",
				"materia", "Programación Web",
				"actividad", "Act1. Introducción a Spring Boot"
		);
	}

	/** b) Información propia: integrantes del equipo 13. */
	@GetMapping("/integrantes")
	public List<Integrante> integrantes() {
		return List.of(
				new Integrante("GVPD", "Perla Danae Gallardo Vasquez", "Desarrolladora"),
				new Integrante("LSR", "Ricardo Lemus Sánchez", "Desarrollador")
		);
	}

	/** c) Respuesta de texto simple, con un parámetro opcional. */
	@GetMapping("/saludo")
	public String saludo(@RequestParam(defaultValue = "visitante") String nombre) {
		return "¡Hola, " + nombre + "! Bienvenida a CulturaVibe.";
	}
}
