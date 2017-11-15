package entities;

public class Lote {

    private final String id;
    private final int tarimas,cajas,cajasTarimas;

    public Lote(String id, int tarimas, int cajas, int cajasTarimas) {
        this.id = id;
        this.tarimas = tarimas;
        this.cajas = cajas;
        this.cajasTarimas = cajasTarimas;
    }

    public String getId() {
        return id;
    }

    public int getTarimas() {
        return tarimas;
    }

    public int getCajas() {
        return cajas;
    }

    public int getCajasTarimas() {
        return cajasTarimas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lote lote = (Lote) o;

        return id.equals(lote.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Lote{" +
                "id='" + id + '\'' +
                ", tarimas=" + tarimas +
                ", cajas=" + cajas +
                ", cajasTarimas=" + cajasTarimas +
                '}';
    }
}
