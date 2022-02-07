package test.monde;

import twisk.monde.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GestionnaireEtapesTest {

    static private GestionnaireEtape gstEtape ;

    @BeforeEach
    void setUp(){
        gstEtape = new GestionnaireEtape();
    }


    @Test
    void ajouterTest(){
        gstEtape.ajouter(new Guichet("0"), new ActiviteRestreinte("1"), new Activite("2"), new Etape("3") {
        });
        assertTrue(gstEtape.nbEtapes() == 4);
    }

    @Test
    void hasNext() {
        Iterator<Etape> iter = gstEtape.iterator() ;
        assertFalse(iter.hasNext());

        gstEtape.ajouter(new SasEntree());
        iter = gstEtape.iterator() ;
        assertTrue(iter.hasNext());
    }

}