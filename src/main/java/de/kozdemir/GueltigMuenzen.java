package de.kozdemir;

public enum GueltigMuenzen {

    ZHEN("10",10),
    ZWANZIG("20",20),
    FUENFZIG("50",50),
    HUNDERT("100",100),
    ZWEI_HUNDERET("200",200);

    private final String name;
    private final int zahl;

    GueltigMuenzen(String name, int zahl) {
        this.name = name;
        this.zahl = zahl;
    }

    public String getName() {
        return name;
    }

    public int getZahl() {
        return zahl;
    }
}