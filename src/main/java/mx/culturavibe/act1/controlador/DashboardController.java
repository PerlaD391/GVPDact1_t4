package mx.culturavibe.act1.controlador;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.culturavibe.act1.modelo.ProximoEvento;
import mx.culturavibe.act1.modelo.ResumenDashboard;
import mx.culturavibe.act1.servicio.DashboardService;

/**
 * Controlador 3: panel principal (dashboard).
 * Es la versión en Spring Boot de la ruta /dashboard de la app de React:
 * las mismas tarjetas (eventos activos, categorías, artistas locales,
 * próximo evento) y los recordatorios de los próximos 7 días.
 */
@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

	private final DashboardService dashboardService;

	public DashboardController(DashboardService dashboardService) {
		this.dashboardService = dashboardService;
	}

	/** Resumen completo del panel principal. */
	@GetMapping("/resumen")
	public ResumenDashboard resumen() {
		return dashboardService.resumen();
	}

	/** Próximo evento con los días que faltan para que ocurra. */
	@GetMapping("/proximo-evento")
	public ProximoEvento proximoEvento() {
		return dashboardService.proximoEvento();
	}
}
