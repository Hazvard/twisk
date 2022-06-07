package twiskIG.vues;

import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import twiskIG.mondeIG.ArcIG;
import twiskIG.mondeIG.MondeIG;

public class VueArcIG extends Group implements Observateur{
    private MondeIG monde;
    private Line ligne;
    private ArcIG arc;

    /**
     * Constructeur
     * @param world
     * @param arc
     */
    public VueArcIG(MondeIG world, ArcIG arc){
        this.monde = world;
        ligne = new Line();
        this.arc = arc;

        Polyline fleche;
        fleche = new Polyline();
        Double x1,y1,x2,y2;
            x2 = (double)this.arc.getPt1X();
            y2 = (double)this.arc.getPt1Y();
            x1 = (double)this.arc.getPt2X();
            y1 = (double)this.arc.getPt2Y();

        int taillePointe = 20;
        int radian = 20;
        //Formule libre de droit//
        double theta = Math.atan2(y2-y1, x2-x1);
        double pt1x = x1 + Math.cos(theta + Math.toRadians(radian)) * taillePointe;
        double pt1y = y1 + Math.sin(theta + Math.toRadians(radian)) * taillePointe;
        double pt2x = x1 + Math.cos(theta - Math.toRadians(radian)) * taillePointe;
        double pt2y = y1 + Math.sin(theta - Math.toRadians(radian)) * taillePointe;

        fleche.getPoints().addAll(new Double[]{
                pt1x,pt1y,
                pt2x,pt2y,
                x1,y1,
        });
        fleche.setFill(Color.RED);

        if(arc.isSelectionner()){
            ligne.setStyle("-fx-stroke: red;");
        }

        ligne.setStartX(arc.getPt1X());
        ligne.setStartY(arc.getPt1Y());
        ligne.setEndX(arc.getPt2X());
        ligne.setEndY(arc.getPt2Y());
        ligne.setStrokeWidth(5);
        this.getChildren().addAll(ligne,fleche);

        this.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
            monde.selectArc(this.arc);
            if(arc.isSelectionner()){
                ligne.setStyle("-fx-stroke: red;");
            }
            else{
                monde.notifierObservateur();
            }
        });
    }


    /**
     * Permet de mettre à jour les vues
     */
    @Override
    public void reagir() {
        //Vide
    }
}
