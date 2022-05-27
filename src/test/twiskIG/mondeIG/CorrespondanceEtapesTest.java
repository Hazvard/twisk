package twiskIG.mondeIG;

import org.junit.jupiter.api.Test;
import twisk.monde.Activite;
import twisk.monde.Etape;
import twisk.monde.Guichet;

import static org.junit.jupiter.api.Assertions.*;

class CorrespondanceEtapesTest {

    @Test
    void ajouter() {
        CorrespondanceEtapes correspondanceEtapes = new CorrespondanceEtapes();
        EtapeIG activiteIG = new ActiviteIG("actIG", "1", 1, 2);
        EtapeIG guichetIG = new GuichetIG("gchIG", "2", 1, 2, 1);

        Etape activite = new Activite("act", 1);
        Etape guichet = new Guichet("gch", 1);

        correspondanceEtapes.ajouter(activiteIG, activite);
        correspondanceEtapes.ajouter(guichetIG, guichet);

        assertEquals(2,correspondanceEtapes.tailleIG());
        assertEquals(2, correspondanceEtapes.taille());

    }

    @Test
    void get() {

    }
}