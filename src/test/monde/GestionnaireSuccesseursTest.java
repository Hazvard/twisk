package test.monde;

import twisk.monde.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GestionnaireSuccesseursTest {

    static private GestionnaireSuccesseurs gstSuccesseurs ;

    @BeforeEach
    void setUp(){
        gstSuccesseurs = new GestionnaireSuccesseurs();
    }


    @Test
    void ajouterTest(){
        gstSuccesseurs.ajouter(new Guichet("0"), new ActiviteRestreinte("1"), new Activite("2") {
        });
        assertTrue(gstSuccesseurs.nbEtapes() == 3);
    }

    @Test
    void hasNext() {
        Iterator<Etape> iter = gstSuccesseurs.iterator() ;
        assertFalse(iter.hasNext());

        gstSuccesseurs.ajouter(new SasEntree(), new Guichet("0"), new ActiviteRestreinte("1"), new Activite("2"));
        iter = gstSuccesseurs.iterator() ;
        while (iter.hasNext()){
            assertTrue(iter.hasNext());
            iter.next();
        }
        assertFalse(iter.hasNext());

    }

}
