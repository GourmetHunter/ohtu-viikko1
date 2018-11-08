package ohtu.ohtuvarasto;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class VarastoTest {

    private Varasto varasto;
    private double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void konstruktori2() {
        Varasto v1 = new Varasto(10.25, 100.123);
        Varasto v2 = new Varasto(200.123, 20.25);
        Varasto v3 = new Varasto(-123.2, 2000.25);
        Varasto v4 = new Varasto(24.3, -100.2);

        assertTrue(v1.getTilavuus() == 10.25);
        assertTrue(v2.getTilavuus() == 200.123);
        assertTrue(v3.getTilavuus() == 0.0);
        assertTrue(v4.getTilavuus() == 24.3);
        assertTrue(v1.getSaldo() == 10.25);
        assertTrue(v2.getSaldo() == 20.25);
        assertTrue(v3.getSaldo() == -123.2);
        assertTrue(v4.getSaldo() == 0.0);
    }

    @Test
    public void konstruktori1() {
        Varasto v = new Varasto(-20);
        assertTrue(v.getTilavuus() == 0.0);
    }

    @Test
    public void lisaaVarastoon() {
        Varasto v = new Varasto(10.0);
        assertTrue(v.getSaldo() == 0);
        v.lisaaVarastoon(-25.0);
        assertTrue(v.getSaldo() == 0);
        v.lisaaVarastoon(2);
        assertTrue(v.getSaldo() == 2);
        v.lisaaVarastoon(9);
        assertTrue(v.getSaldo() == 10);
        v.lisaaVarastoon(-25.0);
        assertTrue(v.getSaldo() == 10);
    }

    @Test
    public void otaVarastosta() {
        Varasto v = new Varasto(10.0);
        assertTrue(v.getSaldo() == 0);
        assertTrue(v.otaVarastosta(-5) == 0);
        assertTrue(v.getSaldo() == 0);
        v.lisaaVarastoon(2.25);
        assertTrue(v.otaVarastosta(1) == 1);
        assertTrue(v.otaVarastosta(2) == 1.25);
    }

    @Test
    public void toStringTest() {
        Varasto v = new Varasto(10.0);
        assertTrue(v.toString().equals("saldo = 0.0, vielä tilaa 10.0"));
        v.lisaaVarastoon(2.25);
        assertTrue(v.toString().equals("saldo = 2.25, vielä tilaa 7.75"));
        v.otaVarastosta(1);
        assertTrue(v.toString().equals("saldo = 1.25, vielä tilaa 8.75"));
    }

}
