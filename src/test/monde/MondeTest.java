package test.monde;

import twisk.monde.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class  MondeTest {

    static private Monde monde ;
    static private Etape sasSortie ;
    static private Etape sasEntree ;
    static private ActiviteRestreinte actRestreinte ;
    static private Guichet guichet ;
    static private Activite activite ;

    @BeforeEach
    void setUp(){
        monde = new Monde() ;
        sasEntree = new SasEntree() ;
        sasSortie = new SasSortie() ;
        actRestreinte = new ActiviteRestreinte("ActResrt");
        guichet = new Guichet("Gch");
        activite = new Activite("act");

    }

    @Test
    void TestACommeEntree(){
        monde.aCommeEntree(activite, guichet);
        assertTrue(monde.nbEtapes() == 2);
    }

    @Test
    void TestACommeSortie(){
        monde.aCommeSortie(activite, guichet);
        assertTrue(monde.nbEtapes() == 2);
    }

    @Test
    void Testajouter(){
        monde.ajouter(activite, actRestreinte, guichet);
    }

    @Test
    void TestnbEtapes(){
        assertTrue(monde.nbEtapes() == 0);
        monde.ajouter(activite, actRestreinte, guichet);
        assertTrue(monde.nbEtapes() == 3);


    }

    @Test
    void TestNbGuichets(){
        assertTrue(monde.nbGuichets() == 0);
        monde.ajouter(activite, actRestreinte, guichet);
        assertTrue(monde.nbGuichets() == 1);
    }

    @Test
    void hasNext() {
        Iterator<Etape> iter = monde.iterator() ;
        assertFalse(iter.hasNext());

        monde.ajouter(new SasEntree(), new Guichet("0"), new ActiviteRestreinte("1"), new Activite("2"));
        iter = monde.iterator() ;
        while (iter.hasNext()){
            assertTrue(iter.hasNext());
            iter.next();
        }
        assertFalse(iter.hasNext());

    }

    @Test
    void toC(){
        monde.toC();
        assertEquals("void simulation(int ids){\nentrer(0);\ntransfert(0, 1);\n\n}", monde.toC());
    }



}
