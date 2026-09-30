package smartlibrary;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Estudiante estudiante = new Estudiante("1085321456", "Laura Gómez", "laura.gomez@universidad.edu.co",
                "EST-2026-014", "Ingeniería de Sistemas");
        Bibliotecario bibliotecario = new Bibliotecario("59834127", "Carlos Rosero", "carlos.rosero@universidad.edu.co",
                "EMP-031", "Mañana");
        Libro libro = new Libro("978-0134685991", "Effective Java", "Joshua Bloch");
        Ejemplar ejemplar = new Ejemplar("EJ-001", libro);
        Prestamo prestamo = new Prestamo(estudiante, ejemplar,
                LocalDate.of(2026, 9, 24), LocalDate.of(2026, 10, 8));

        System.out.println("=== Préstamo inicial ===");
        System.out.println("Estudiante: " + estudiante.getNombre());
        System.out.println("Bibliotecario de turno: " + bibliotecario.getNombre());
        System.out.println("Ejemplar: " + ejemplar.getCodigoEjemplar() + " - " + libro.getTitulo()
                + " (" + ejemplar.getEstado() + ")");
        System.out.println("Fecha prevista de devolución: " + prestamo.getFechaPrevistaDevolucion());

        System.out.println();
        System.out.println("=== Prueba 1: renovación válida (nueva fecha 2026-10-15) ===");
        prestamo.renovar(LocalDate.of(2026, 10, 15), LocalDate.of(2026, 10, 6));
        estudiante.notificar("Su préstamo fue renovado.");
        System.out.println("Nueva fecha prevista: " + prestamo.getFechaPrevistaDevolucion());
        System.out.println("Cantidad de renovaciones: " + prestamo.getCantidadRenovaciones());
        Renovacion r = prestamo.getRenovaciones().get(0);
        System.out.println("Historial -> anterior: " + r.getFechaAnterior() + ", nueva: " + r.getNuevaFecha()
                + ", realizada el: " + r.getFechaRenovacion());

        System.out.println();
        System.out.println("=== Prueba 2: renovación inválida (nueva fecha igual: 2026-10-15) ===");
        intentarRenovar(prestamo, LocalDate.of(2026, 10, 15));

        System.out.println();
        System.out.println("=== Prueba 3: renovación inválida (nueva fecha anterior: 2026-10-10) ===");
        intentarRenovar(prestamo, LocalDate.of(2026, 10, 10));
    }

    private static void intentarRenovar(Prestamo prestamo, LocalDate nuevaFecha) {
        try {
            prestamo.renovar(nuevaFecha);
            System.out.println("ERROR: la renovación no debió aceptarse.");
        } catch (IllegalArgumentException e) {
            System.out.println("Rechazada -> " + e.getMessage());
        }
        System.out.println("Fecha prevista se mantiene: " + prestamo.getFechaPrevistaDevolucion());
        System.out.println("Cantidad de renovaciones se mantiene: " + prestamo.getCantidadRenovaciones());
    }
}
