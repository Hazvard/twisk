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

        monde.setNumSortie(5);

        Activite activite1 = new Activite("Act01", 6, 3, 1);
        Guichet guichet1 = new Guichet("Guichet",3,2, 4 );
        ActiviteRestreinte activiteRestreinte = new ActiviteRestreinte("ActRest", 6, 3, 3 );
        Activite activite2 = new Activite("Act02", 6, 3, 4);
        activite1.ajouterSuccesseur(guichet1);
        guichet1.ajouterSuccesseur(activiteRestreinte);
        activiteRestreinte.ajouterSuccesseur(activite2);
        monde.aCommeEntree(activite1);
        monde.aCommeSortie(activite2);
        monde.toC();



        assertEquals("void simulation(int ids){\n" +
                "\n" +
                "  entrer(0);\n" +
                "  transfert(0, 1);// L'entrée transfere à la première act\n" +
                "\n" +
                "  delai(6, 3);\n" +
                "  transfert(1, 2);\n" +
                "\n" +
                "  P(ids,4);\n" +
                "  transfert(2, 3);\n" +
                "  delai(3,1);\n" +
                "  transfert(3, 4);\n" +
                "  V(ids, 4);\n" +
                "\n" +
                "  delai(6, 3);\n" +
                "  transfert(4, 5);\n" +
                "\n" +
                "    //Sortie\n" +
                "}", monde.toC());
    }



}
