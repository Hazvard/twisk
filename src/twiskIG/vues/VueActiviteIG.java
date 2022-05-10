package twiskIG.vues;

import javafx.geometry.Pos;
import twiskIG.mondeIG.EtapeIG;
import twiskIG.mondeIG.MondeIG;

import java.awt.*;

public class VueActiviteIG extends VueEtapeIG implements Observateur{

    private MondeIG monde;
    private EtapeIG etape;
    private Label lab;

    public VueActiviteIG(MondeIG world, EtapeIG etape) {
        super(world, etape);
    }

    @Override
    public void reagir() {
        this.getChildren().clear();
    }
}
