package twiskIG.tests;

import org.junit.jupiter.api.BeforeEach;
import twiskIG.mondeIG.ActiviteIG;
import twiskIG.mondeIG.ArcIG;
import twiskIG.mondeIG.PointDeControlIG;
import twiskIG.outils.FabriqueIdentifiant;

public class TestArcIG {
    ArcIG arc;
    FabriqueIdentifiant fab;
    ActiviteIG activite;
    ActiviteIG activite2;
    PointDeControlIG pdc;
    PointDeControlIG pdc2;

    @BeforeEach
    void SetUp(){
        fab = FabriqueIdentifiant.getInstance();
        activite = new ActiviteIG("Activité", fab.getIdentiantEtape(), 10,10);
        activite2 = new ActiviteIG("Activite", fab.getIdentiantEtape(), 10,10);
        pdc = new PointDeControlIG(activite);
        pdc2 = new PointDeControlIG(activite2);
        arc = new ArcIG(pdc, pdc2);
    }

    @org.junit.jupiter.api.Test
    void testArcIG(){
        System.out.println(arc.toString());
    }
}
