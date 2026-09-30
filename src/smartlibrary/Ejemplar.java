package smartlibrary;

public class Ejemplar {
    private String codigoEjemplar;
    private String estado;
    private Libro libro;

    public Ejemplar(String codigoEjemplar, Libro libro) {
        if (libro == null) {
            throw new IllegalArgumentException("Todo ejemplar debe corresponder a un libro.");
        }
        this.codigoEjemplar = codigoEjemplar;
        this.libro = libro;
        this.estado = "DISPONIBLE";
    }

    public String getCodigoEjemplar() {
        return codigoEjemplar;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Libro getLibro() {
        return libro;
    }
}
