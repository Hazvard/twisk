package test.outils;

import twisk.monde.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.outils.FabriqueNumero;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FabriqueNuméroTest {
    static private FabriqueNumero fabriqueNumero ;

    @BeforeEach
    void setUp() {
        fabriqueNumero = FabriqueNumero.getInstance() ;
    }

    @Test
    void testGetNumero(){
        assertTrue(fabriqueNumero.getNumeroEtpe() == 0);
        assertTrue(fabriqueNumero.getNumeroEtpe() == 1);
        assertTrue(fabriqueNumero.getNumeroEtpe() == 2);
        assertTrue(fabriqueNumero.getNumeroEtpe() == 3);
    }

    @Test
    void testGetNumeroMonde(){
        assertTrue(fabriqueNumero.getNumeroMonde() == 0);
        assertTrue(fabriqueNumero.getNumeroMonde() == 1);
        assertTrue(fabriqueNumero.getNumeroMonde() == 2);
        assertTrue(fabriqueNumero.getNumeroMonde() == 3);
    }


    @Test
    void testGetCptSemaphore(){
        assertTrue(fabriqueNumero.getCptSemaphore() == 1);
        assertTrue(fabriqueNumero.getCptSemaphore() == 2);
        assertTrue(fabriqueNumero.getCptSemaphore() == 3);
        assertTrue(fabriqueNumero.getCptSemaphore() == 4);
    }


    @Test
    void testReset(){
        assertTrue(fabriqueNumero.getNumeroEtpe() == 0);
        fabriqueNumero.reset();

        assertTrue(fabriqueNumero.getCptSemaphore() == 1);
        fabriqueNumero.reset();

        assertTrue(fabriqueNumero.getNumeroEtpe() == 0);
        assertTrue(fabriqueNumero.getCptSemaphore() == 1);


        assertTrue(fabriqueNumero.getNumeroEtpe() == 1);
        assertTrue(fabriqueNumero.getCptSemaphore() == 2);
        assertTrue(fabriqueNumero.getCptSemaphore() == 3);
        assertTrue(fabriqueNumero.getNumeroEtpe() == 2);
        fabriqueNumero.reset();
        assertTrue(fabriqueNumero.getNumeroEtpe() == 0);
        assertTrue(fabriqueNumero.getCptSemaphore() == 1);
    }


}
