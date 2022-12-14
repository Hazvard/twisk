package monde;

import twisk.monde.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void toC(){
        activite = new Activite("Act001", 6, 3, 1);
        activiteRestreinte = new ActiviteRestreinte("Actres", 6, 3, 3);
        guichet = new Guichet("Gct001", 2);
        sasEntree = new SasEntree(0);
        sasSortie = new SasSortie(4);


        sasEntree.ajouterSuccesseur(activite);
        activite.ajouterSuccesseur(guichet);
        guichet.ajouterSuccesseur(activiteRestreinte);
        activiteRestreinte.ajouterSuccesseur(sasSortie);

        String entre = sasEntree.toC();

        assertEquals("\n" +
                "  entrer(0);\n" +
                "  delai(0, 0);\n" +
                "  transfert(0, 1);// L'entr??e transfere ?? la premi??re act\n" +
                "\n" +
                "  delai(6, 3);\n" +
                "  transfert(1, 5);\n" +
                "\n" +
                "  P(ids,2);\n" +
                "  transfert(5, 3);\n" +
                "  delai(6,3);\n" +
                "  V(ids, 2);\n" +
                "  transfert(3, 4);\n" +
                "\n" +
                "    //Sortie\n", entre);


    }




}
