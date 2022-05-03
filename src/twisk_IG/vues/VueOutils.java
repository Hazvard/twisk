package twisk_IG.vues;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.TilePane;
import twisk_IG.mondeIG.MondeIG;

public class VueOutils extends TilePane implements Observateur {

    private MondeIG mondeIG;
    private Button button;

    public VueOutils(MondeIG mondeIG){
        this.mondeIG = mondeIG ;
        this.mondeIG.ajouterObservateur(this);
        button = new Button("Activité");
        button.setStyle("-fx-font: 22 Impact ; -fx-background-color: #BEC2BE ; -fx-border-radius: 6  ; -fx-text-fill: #606668 ; -fx-border-width: 0");

        Tooltip tooltip = new Tooltip("Ajouter une activité");
        button.setTooltip(tooltip);

        button.setOnAction(event ->{
            mondeIG.ajouter("Activité");
        });

        this.setAlignment(Pos.TOP_LEFT);
        this.getChildren().addAll(button);
    }



    @Override
    public void reagir() {

    }
}
