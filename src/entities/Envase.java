package entities;

public class Envase {
    private final String description;
    private final int milis;

    public Envase(String description, int milis) {
        this.description = description;
        this.milis = milis;
    }

    public String getDescription() {
        return description;
    }

    public int getMilis() {
        return milis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Envase envase = (Envase) o;

        if (milis != envase.milis) return false;
        return description.equals(envase.description);
    }

    @Override
    public int hashCode() {
        int result = description.hashCode();
        result = 31 * result + milis;
        return result;
    }

    @Override
    public String toString() {
        return "Envase{" +
                "description='" + description + '\'' +
                ", milis=" + milis +
                '}';
    }
}
