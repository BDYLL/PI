package entities;

public class ProductoEnvasado {
    private final String idCodigo,description,fechaEntrada,fechaSalida;
    private final int milis,edad,cantidad;

    public int getEdad() {
        return edad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public ProductoEnvasado(String idCodigo, String description, String fechaEntrada, String fechaSalida, int milis, int cantidad, int edad) {
        this.idCodigo = idCodigo;
        this.description = description;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.milis = milis;
        this.cantidad = cantidad;
        this.edad=edad;
    }

    public ProductoEnvasado(String idCodigo, String description, int milis) {
        this.idCodigo=idCodigo;
        this.description=description;
        this.milis=milis;
        fechaEntrada="";
        fechaSalida="";
        cantidad=0;
        edad=0;
    }

    @Override
    public String toString() {
        return "IngresarProductoEnvasado{" +
                "idCodigo='" + idCodigo + '\'' +
                ", description='" + description + '\'' +
                ", fechaEntrada='" + fechaEntrada + '\'' +
                ", fechaSalida='" + fechaSalida + '\'' +
                ", milis=" + milis +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductoEnvasado that = (ProductoEnvasado) o;

        if (milis != that.milis) return false;
        if (!idCodigo.equals(that.idCodigo)) return false;
        return description.equals(that.description);
    }

    @Override
    public int hashCode() {
        int result = idCodigo.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + milis;
        return result;
    }

    public String getIdCodigo() {
        return idCodigo;
    }

    public String getDescription() {
        return description;
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public int getMilis() {
        return milis;
    }
}
