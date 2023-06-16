package kozdemir; /**
 * Created By hasan
 * Date : 15.06.2023
 */

import de.kozdemir.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;


public class GetraenkeautomatTest {

    private Getraenkeautomat automat = new Getraenkeautomat();


    @ParameterizedTest
    @MethodSource("getTestData")
    void testKaufen(Getraenk getraenk, List<Muenze> einzahlung, GetraenkUndWechselgeld expectedOutput) {

        // Getränkebestand hinzufügen
        automat.getraenkHinzufuegen(new Getraenk( GetraenkName.COLA, 120), 5);
        automat.getraenkHinzufuegen(new Getraenk( GetraenkName.WASSER, 80), 10);
        automat.getraenkHinzufuegen(new Getraenk( GetraenkName.SAFT, 150), 3);

        // Münzbestand hinzufügen
        automat.muenzeHinzufuegen(10, 100);
        automat.muenzeHinzufuegen(20, 100);
        automat.muenzeHinzufuegen(50, 50);
        automat.muenzeHinzufuegen(100, 50);
        automat.muenzeHinzufuegen(200, 20);

        GetraenkUndWechselgeld result = automat.kaufen(getraenk, einzahlung);
        Assertions.assertEquals(expectedOutput, result);
    }

    // Test data generator method
    private static List<Object[]> getTestData() {
        Getraenk cola = new Getraenk( GetraenkName.COLA, 120);
        Getraenk wasser = new Getraenk( GetraenkName.WASSER, 80);

        return Arrays.asList(
                new Object[]{cola, Arrays.asList(new Muenze(200)), new GetraenkUndWechselgeld(cola, Arrays.asList(new Muenze(50), new Muenze(20), new Muenze(10)))},
                new Object[]{wasser, Arrays.asList(new Muenze(100)), new GetraenkUndWechselgeld(wasser, Arrays.asList(new Muenze(20)))}
                  // Add more test cases
        );
    }
}
