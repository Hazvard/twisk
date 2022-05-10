package twiskIG.tests;

import twiskIG.mondeIG.EtapeIG;
import twiskIG.mondeIG.ActiviteIG;

import org.junit.jupiter.api.BeforeEach;
import twiskIG.outils.FabriqueIdentifiant;

public class TestActiviteIG {
    ActiviteIG activite;
    ActiviteIG activite2;
    FabriqueIdentifiant fab;

    @BeforeEach
    void setUp(){
        fab = FabriqueIdentifiant.getInstance();
        activite = new ActiviteIG("Activité", fab.getIdentiantEtape(), 10,10);
        activite2 = new ActiviteIG("Activite", fab.getIdentiantEtape(), 10,10);
    }


    @org.junit.jupiter.api.Test
    void testActIG(){
        System.out.println(activite.getIdentifiant()+" / "+activite2.getIdentifiant());
        assert(activite.getIdentifiant() != "no Etape : 0");
        assert(activite2.getIdentifiant() != "no Etape : 1");
    }
}
