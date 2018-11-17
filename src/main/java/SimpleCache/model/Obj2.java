package SimpleCache.model;

import java.util.Objects;

public class Obj2 {
    private String descr;

    public Obj2(String descr) {
        this.descr = descr;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Obj2)) return false;
        Obj2 obj1 = (Obj2) o;
        return Objects.equals(descr, obj1.descr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descr);
    }

    @Override
    public String toString() {
        return "Obj2. Descr: " + descr;
    }
}
