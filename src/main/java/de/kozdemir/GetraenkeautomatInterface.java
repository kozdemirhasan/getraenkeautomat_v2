package de.kozdemir;

import java.util.List;


public interface GetraenkeautomatInterface {
    void getraenkHinzufuegen(Getraenk getraenk, int anzahl);

    void muenzeHinzufuegen(int wert, int anzahl);

    GetraenkUndWechselgeld kaufen(Getraenk getraenk, List<Muenze> einzahlung) throws MyCustomException;

    void alleGeldAbladen();

}
