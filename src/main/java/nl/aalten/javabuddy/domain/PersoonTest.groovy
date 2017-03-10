package nl.aalten.javabuddy.domain

import org.bouncycastle.util.test.Test
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDate

/**
 * Created by HoltropA on 10-3-2017.
 */

//public class PersoonTest extends groovy.util.GroovyTestCase {
public class PersoonTest {

@Test
    void testMaakNieuweKlantAan() {
     Persoon klant = new Persoon("", "", LocalDate.of(1900, 1, 1));
     testMaakNieuweKlantAan(klant.bsnNummer, klant.naam, LocalDate.of(1900, 1, 1));
    }
@Test
    void testMaakNieuweRekeningAan() {

    }
}
