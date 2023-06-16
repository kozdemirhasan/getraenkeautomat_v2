package de.kozdemir;

import java.util.List;
import java.util.Objects;

/**
 * Created By hasan
 * Date : 15.06.2023
 */
public class GetraenkUndWechselgeld {
    private Getraenk getraenk;
    private List<Muenze> wechselgeld;
    private String message;

    public GetraenkUndWechselgeld(Getraenk getraenk, List<Muenze> wechselgeld, String message) {
        this.getraenk = getraenk;
        this.wechselgeld = wechselgeld;
        this.message=message;

    }

    public GetraenkUndWechselgeld(Getraenk getraenk, List<Muenze> wechselgeld) {
        this.getraenk = getraenk;
        this.wechselgeld = wechselgeld;
    }

    public Getraenk getGetraenk() {
        return getraenk;
    }

    public List<Muenze> getWechselgeld() {
        return wechselgeld;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetraenkUndWechselgeld that = (GetraenkUndWechselgeld) o;
        return Objects.equals(getraenk, that.getraenk) && Objects.equals(wechselgeld, that.wechselgeld);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getraenk, wechselgeld);
    }

    @Override
    public String toString() {
        return "GetraenkUndWechselgeld{" +
                "getraenk=" + getraenk +
                ", wechselgeld=" + wechselgeld +
                '}';
    }
}
