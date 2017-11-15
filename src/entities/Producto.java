package entities;

public class Producto {

    private final String idCodigo;

    public Producto(String codigo) {
        this.idCodigo = codigo;
    }

    public String getIdCodigo() {
        return idCodigo;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "idCodigo='" + idCodigo + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Producto producto = (Producto) o;

        return idCodigo.equals(producto.idCodigo);
    }

    @Override
    public int hashCode() {
        return idCodigo.hashCode();
    }
}
