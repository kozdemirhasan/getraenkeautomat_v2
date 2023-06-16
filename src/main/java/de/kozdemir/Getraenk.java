package de.kozdemir;

import java.util.Objects;

/**
 * Created By hasan
 * Date : 15.06.2023
 */
public class Getraenk {
    private GetraenkName name;
    private int preis;

    public Getraenk(GetraenkName name) {
        this.name = name;
    }

    public Getraenk(GetraenkName name, int preis) {
        this.name = name;
        this.preis = preis;
    }

    public GetraenkName getName() {
        return name;
    }

    public int getPreis() {
        return preis;
    }


    public void setName(GetraenkName name) {
        this.name = name;
    }

    public void setPreis(int preis) {
        this.preis = preis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Getraenk getraenk = (Getraenk) o;
        return name.toString().equals(getraenk.getName().toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return "Getraenk{" +
                "name=" + name +
                ", preis=" + preis +
                '}';
    }
}
