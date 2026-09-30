package smartlibrary;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Prestamo {
    private Estudiante estudiante;
    private Ejemplar ejemplar;
    private LocalDate fechaPrestamo;
    private LocalDate fechaPrevistaDevolucion;
    private List<Renovacion> renovaciones = new ArrayList<>();

    public Prestamo(Estudiante estudiante, Ejemplar ejemplar,
                    LocalDate fechaPrestamo, LocalDate fechaPrevistaDevolucion) {
        if (estudiante == null || ejemplar == null) {
            throw new IllegalArgumentException("Un préstamo requiere un estudiante y un ejemplar.");
        }
        if (fechaPrevistaDevolucion == null || !fechaPrevistaDevolucion.isAfter(fechaPrestamo)) {
            throw new IllegalArgumentException("La fecha prevista de devolución debe ser posterior a la fecha del préstamo.");
        }
        this.estudiante = estudiante;
        this.ejemplar = ejemplar;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaPrevistaDevolucion = fechaPrevistaDevolucion;
        ejemplar.setEstado("PRESTADO");
    }

    public void renovar(LocalDate nuevaFecha) {
        renovar(nuevaFecha, LocalDate.now());
    }

    public void renovar(LocalDate nuevaFecha, LocalDate fechaRenovacion) {
        if (nuevaFecha == null || !nuevaFecha.isAfter(fechaPrevistaDevolucion)) {
            throw new IllegalArgumentException("Renovación inválida: la nueva fecha (" + nuevaFecha
                    + ") debe ser posterior a la fecha prevista vigente (" + fechaPrevistaDevolucion + ").");
        }
        Renovacion renovacion = new Renovacion(fechaRenovacion, fechaPrevistaDevolucion, nuevaFecha);
        renovaciones.add(renovacion);
        fechaPrevistaDevolucion = nuevaFecha;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public Ejemplar getEjemplar() {
        return ejemplar;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public LocalDate getFechaPrevistaDevolucion() {
        return fechaPrevistaDevolucion;
    }

    public List<Renovacion> getRenovaciones() {
        return Collections.unmodifiableList(renovaciones);
    }

    public int getCantidadRenovaciones() {
        return renovaciones.size();
    }
}
