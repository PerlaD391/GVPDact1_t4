package mx.culturavibe.act1.modelo;

/** Próximo evento con los días que faltan para que ocurra. */
public record ProximoEvento(
		Evento evento,
		long diasRestantes
) {
}
