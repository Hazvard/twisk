package test.monde;

import twisk.monde.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EtapeTest {

    static private Etape sasSortie ;
    static private Etape sasEntree ;
    static private Etape activiteRestreinte ;
    static private Etape activite ;
    static private Etape guichet ;

    @BeforeEach
    void setUp() {
        sasSortie = new SasSortie();
        sasEntree = new SasEntree();
        activiteRestreinte = new ActiviteRestreinte("ActRst001");
        activite = new Activite("Act001");
        guichet = new Guichet("Gct001");
    }

    @Test
    void testAjouterSuccesseur(){
        activite.ajouterSuccesseur(sasEntree, sasSortie, guichet, activiteRestreinte);
        assertTrue(activite.nbSuccesseurs() == 4);
    }

    @Test
    void testEstUneActivite(){
        assertTrue(sasSortie.estUneActivite());
        assertTrue(sasEntree.estUneActivite());
        assertTrue(activiteRestreinte.estUneActivite());
        assertTrue(activite.estUneActivite());
        assertFalse(guichet.estUneActivite());

    }

    @Test
    void testEstUnGuichet(){
        assertFalse(sasSortie.estUnGuichet());
        assertFalse(sasEntree.estUnGuichet());
        assertFalse(activiteRestreinte.estUnGuichet());
        assertFalse(activite.estUnGuichet());
        assertTrue(guichet.estUnGuichet());
    }

    @Test
    void hasNext() {
        Iterator<Etape> iter = activite.iterator() ;
        assertFalse(iter.hasNext());

        activite.ajouterSuccesseur(sasSortie, sasEntree, activiteRestreinte, guichet, activite);
        iter = activite.iterator() ;
        while (iter.hasNext()){
            assertTrue(iter.hasNext());
            iter.next();
        }
        assertFalse(iter.hasNext());
    }
}
