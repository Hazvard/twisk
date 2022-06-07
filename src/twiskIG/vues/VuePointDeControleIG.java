package twiskIG.vues;

import javafx.scene.control.Alert;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import twiskIG.exceptions.TwiskException;
import twiskIG.mondeIG.EtapeIG;
import twiskIG.mondeIG.MondeIG;
import twiskIG.mondeIG.PointDeControlIG;
import javafx.scene.input.MouseEvent;


public class VuePointDeControleIG extends Circle implements Observateur{
    private MondeIG monde;
    private EtapeIG etape;
    private PointDeControlIG pdc;

    /**
     * Constructeur
     * @param world
     * @param et
     * @param pdc
     */
    public VuePointDeControleIG(MondeIG world, EtapeIG et, PointDeControlIG pdc){
        this.monde = world;
        this.etape = et;
        this.pdc = pdc;
        this.setCenterX(pdc.getX());
        this.setCenterY(pdc.getY());
        this.setRadius(6);
        this.reagir();
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
            try {
                monde.pointDeControleCliquer(pdc);
            } catch (TwiskException ex) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("ATTENTION");
                alert.setHeaderText("Impossible de créer un arc");
                alert.setContentText(ex.getText());
                alert.showAndWait();
                pdc.deselectionner();
            }
            //System.out.println(pdc.toString() + "selectionné !\n");
            monde.notifierObservateur();
        });
    }

    public void changerCouleur(Paint couleur){
        this.setFill(couleur);
    }

    /**
     * Permet de mettre à jour les vues
     */
    @Override
    public void reagir() {
        if(this.pdc.isEstSelec()){
            this.changerCouleur(Color.RED);
        }
    }
}
