package de.kozdemir;

import java.util.Objects;

public class Muenze {
	private int wert;

	public Muenze(int wert) {
		this.wert = wert;
	}

	public int getWert() {
		return wert;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Muenze muenze = (Muenze) o;
		return wert == muenze.wert;
	}

	@Override
	public int hashCode() {
		return Objects.hash(wert);
	}

	@Override
	public String toString() {
		return "Muenze{" +
				"wert=" + wert +
				'}';
	}
}
