package SimpleCache.model;

import java.util.Objects;

public class Obj1{
    private String descr;

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Obj1(String descr) {
        this.descr = descr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Obj1)) return false;
        Obj1 obj1 = (Obj1) o;
        return Objects.equals(descr, obj1.descr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descr);
    }

    @Override
    public String toString() {
        return "Obj1. Descr: " + descr;
    }
}
