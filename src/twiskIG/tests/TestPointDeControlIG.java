package twiskIG.tests;

import twiskIG.mondeIG.ActiviteIG;
import twiskIG.mondeIG.EtapeIG;
import twiskIG.mondeIG.PointDeControlIG;
import org.junit.jupiter.api.BeforeEach;
import twiskIG.outils.FabriqueIdentifiant;

public class TestPointDeControlIG {
    ActiviteIG activiteIG;
    PointDeControlIG pdc;
    FabriqueIdentifiant fab;


    @BeforeEach
    void setup(){
        fab = FabriqueIdentifiant.getInstance();
        activiteIG = new ActiviteIG("Activité", fab.getIdentiantEtape(), 10,10);
    }

    @org.junit.jupiter.api.Test
    void testPointDeControlIG(){
        System.out.println(activiteIG.toString());
    }

}
