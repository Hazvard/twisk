package monde;

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
        monde.ajouter(activite, guichet, actRestreinte);
        monde.aCommeEntree(activite, guichet);
        assertTrue(monde.nbEtapes() == 3);
    }

    @Test
    void TestACommeSortie(){
        monde.ajouter(activite, guichet, actRestreinte);
        monde.aCommeSortie(activite, guichet);
        assertTrue(monde.nbEtapes() == 3);
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
    void includePourToC() {
        monde.aCommeEntree(activite);
        monde.aCommeSortie(new Activite("Mabite"));
        monde.ajouter(actRestreinte);
        System.out.println(monde.definetoC());
    }

    @Test
    void toC(){

        //monde.setNumSortie(5);


        activite.ajouterSuccesseur(guichet);
        guichet.ajouterSuccesseur(actRestreinte);

        monde.ajouter(activite, guichet, actRestreinte );

        monde.aCommeEntree(activite);
        monde.aCommeSortie(actRestreinte);
        monde.toC();



        assertEquals("#include \"def.h\"\n" +
                "\n" +
                "\n" +
                "void simulation(int ids){\n" +
                "\n" +
                "  entrer(0);\n" +
                "  delai(3, 2);\n" +
                "  transfert(0, 6);// L'entrée transfere à la première act\n" +
                "\n" +
                "  delai(3, 2);\n" +
                "  transfert(6, 5);\n" +
                "\n" +
                "  P(ids,1);\n" +
                "  transfert(5, 4);\n" +
                "  delai(3,2);\n" +
                "  V(ids, 1);\n" +
                "  transfert(4, 1);\n" +
                "\n" +
                "    //Sortie\n" +
                "}", monde.toC());
    }



}
