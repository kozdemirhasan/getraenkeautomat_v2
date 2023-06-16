package de.kozdemir;

import javax.imageio.plugins.tiff.GeoTIFFTagSet;
import java.util.*;

public class Getraenkeautomat implements GetraenkeautomatInterface {
    private Map<Getraenk, Integer> warenbestand;
    private Map<Integer, Integer> muenzbestand;

    public Getraenkeautomat() {
        warenbestand = new HashMap<>();
        muenzbestand = new HashMap<>();
    }

    public void getraenkHinzufuegen(Getraenk getraenk, int anzahl) {

        Getraenk gesuchterSchluessel = searchGetraenk(getraenk);

        // Falls der gesuchte Schlüssel im Map gefunden wurde
        if (gesuchterSchluessel != null) {
            int neuerPreis = getraenk.getPreis(); // Neuer Preiswert
            Integer alteAnzahl = warenbestand.get(gesuchterSchluessel); // Alte Anzahl

            gesuchterSchluessel.setName(getraenk.getName());
            gesuchterSchluessel.setPreis(getraenk.getPreis()); // Name und neuen Preis des gesuchten Schlüssels aktualisieren

            warenbestand.remove(getraenk); // Entferne das Getränk aus der Map
            warenbestand.put(gesuchterSchluessel, (alteAnzahl + anzahl)); // Füge den gesuchten Schlüssel mit aktualisierter Anzahl hinzu
        } else {
            // Falls der gesuchte Schlüssel nicht im Map gefunden wurde, füge das Getränk mit der angegebenen Anzahl hinzu
            warenbestand.merge(getraenk, anzahl, (existierenderWert, neuerWert) -> existierenderWert + neuerWert);
        }
    }

    private Getraenk searchGetraenk(Getraenk getraenk) {
        Getraenk gesuchterSchluessel = null;
        // Durchlaufe die Schlüssel im warenbestand-Map
        for (Getraenk key : warenbestand.keySet()) {
            // Überprüfe, ob der Name des Schlüssels dem Namen des übergebenen Getränks entspricht
            if (key.getName().toString().equals(getraenk.getName().toString())) {
                gesuchterSchluessel = key;
                break;
            }
        }
        return gesuchterSchluessel;
    }

    @Override
    public void muenzeHinzufuegen(int wert, int anzahl) {
        muenzbestand.put(wert, anzahl);

    }

    @Override
    public GetraenkUndWechselgeld kaufen(Getraenk getraenk, List<Muenze> einzahlung)  {
        int getraenkPreis=0;

        //icecek makinada mevcut ise
        if(searchGetraenk(getraenk) !=null){
            getraenkPreis = searchGetraenk(getraenk).getPreis();
            getraenk.setPreis(getraenkPreis); //arzu edilen icecegin fiyatini nesnemize set ediyoruz
        }else {
            //ürün makinada yok ise
            return new GetraenkUndWechselgeld(null, einzahlung, "Das gewünschte Produkt ist derzeit nicht verfügbar");
        }


        int einzahlungWert = 0;
        for (Muenze muenze : einzahlung) {
            einzahlungWert += muenze.getWert();
        }

        //Reicht das bezahlte Geld aus, um das Produkt kaufen zu können?
        if (getraenkPreis > einzahlungWert) {
            return new GetraenkUndWechselgeld(null, einzahlung, "unzureichende Bezahlung");
        }

        //ist gewünschtes Produkt in der Getränkeautomat?
        if (!warenbestand.containsKey(getraenk) || warenbestand.get(getraenk) == 0) {
            return new GetraenkUndWechselgeld(null, einzahlung,"Das gewünschte Produkt ist derzeit nicht verfügbar2");
        }

        //berechnen wechseln Geld
        int wechselgeld = einzahlungWert - getraenkPreis;
        List<Muenze> herausgegebenesWechselgeld = berechneWechselgeld(wechselgeld);

        if (herausgegebenesWechselgeld == null) {
            return new GetraenkUndWechselgeld(null, einzahlung, "Für eine Rückerstattung ist nicht genügend Geld im Automaten vorhanden");
        }

        warenbestand.put(getraenk, warenbestand.get(getraenk) - 1);

        // Fügen wir das gegebene Geld zu den Münzen im Automaten hinzu
        for (Muenze muenze : einzahlung) {
            muenzbestand.put(muenze.getWert(), muenzbestand.getOrDefault(muenze.getWert(), 0) + 1);
        }
        return new GetraenkUndWechselgeld(getraenk, herausgegebenesWechselgeld,"Kauf erfolgreich. Guten Appetit");
    }

    @Override
    public void alleGeldAbladen() {
        //Wir leeren alle Münzen aus dem Automaten
        muenzbestand.clear();
    }

    private List<Muenze> berechneWechselgeld(int betrag) {
        List<Muenze> wechselgeld = new ArrayList<>();
        int[] muenzwerte = {200, 100, 50, 20, 10};

        for (int muenzwert : muenzwerte) {
            int anzahl = betrag / muenzwert;
            int verfuegbareMuenzen = muenzbestand.getOrDefault(muenzwert, 0);

            anzahl = Math.min(anzahl, verfuegbareMuenzen);

            for (int i = 0; i < anzahl; i++) {
                wechselgeld.add(new Muenze(muenzwert));
            }

            betrag -= anzahl * muenzwert;
        }

        if (betrag == 0) {
            for (Muenze muenze : wechselgeld) {
                muenzbestand.put(muenze.getWert(), muenzbestand.get(muenze.getWert()) - 1);
            }

            return wechselgeld;
        }

        return null;
    }


    public Map<Getraenk, Integer> getWarenbestand() {
        return warenbestand;
    }

    public void setWarenbestand(Map<Getraenk, Integer> warenbestand) {
        this.warenbestand = warenbestand;
    }

    public Map<Integer, Integer> getMuenzbestand() {
        return muenzbestand;
    }

    public void setMuenzbestand(Map<Integer, Integer> muenzbestand) {
        this.muenzbestand = muenzbestand;
    }
}
