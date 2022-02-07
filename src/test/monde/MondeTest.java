package test.monde;

import twisk.monde.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MondeTest {

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

    }

    @Test
    void TestACommeEntree(){

    }

    @Test
    void TestACommeSortie(){

    }

    @Test
    void Testajouter(){
        monde.ajouter(activite, actRestreinte, guichet);
    }

    @Test
    void TestnbEtapes(){
        monde.ajouter(activite, actRestreinte, guichet);


    }

    @Test
    void TestNbGuichets(){

    }

    @Test
    void hasNext(){

    }
}
