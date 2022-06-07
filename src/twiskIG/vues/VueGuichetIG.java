package twiskIG.vues;

import javafx.scene.control.Label;
import twiskIG.mondeIG.EtapeIG;
import twiskIG.mondeIG.GuichetIG;
import twiskIG.mondeIG.MondeIG;

public class VueGuichetIG extends VueEtapeIG implements Observateur{

    /**
     * Constructeur
     * @param world
     * @param etape
     */
    public VueGuichetIG(MondeIG world, EtapeIG etape) {
        super(world, etape);
    }

    /**
     * Permet de mettre à jour les vues
     */
    @Override
    public void reagir(){
        this.getChildren().clear();
    }
}
