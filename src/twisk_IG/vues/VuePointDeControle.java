package twisk_IG.vues;

import javafx.animation.PauseTransition;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import twisk_IG.mondeIG.EtapeIG;
import twisk_IG.mondeIG.MondeIG;
import twisk_IG.mondeIG.PointDeControleIG;

public class VuePointDeControle extends Circle {

    protected EtapeIG etapeIG;
    protected MondeIG mondeIG;
    protected PointDeControleIG point;

    public VuePointDeControle(MondeIG mondeIG, EtapeIG etapeIG, PointDeControleIG pointDeControleIG){

        this.mondeIG = mondeIG;
        this.etapeIG = etapeIG;
        this.point = pointDeControleIG;


        this.setFill(Color.rgb(252, 48, 34));
        this.setCenterX(point.getPosX());
        this.setCenterY(point.getPosY());
        this.setRadius(5);

        this.setOnMouseClicked(event ->{
            try {
                mondeIG.relierDeuxPoints(point);
            }catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Erreur de connection");
                alert.setHeaderText("Vous ne pouvez pas connecter une Etape à elle même.");
                alert.setContentText("L'arc ne sera pas créé.");

                alert.show();
                PauseTransition pause = new PauseTransition(Duration.seconds(5));
                pause.setOnFinished(n -> alert.close() );
                pause.play();
                mondeIG.nullPtsControle();


            }

        });

    }
}
