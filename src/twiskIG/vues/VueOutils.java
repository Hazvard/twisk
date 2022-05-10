package twiskIG.vues;

import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import twiskIG.mondeIG.EtapeIG;
import twiskIG.mondeIG.MondeIG;

public class VueOutils extends TilePane implements Observateur{
    private MondeIG monde;
    private Button addActi;
    private Button addGuichet;

    public VueOutils(MondeIG world){
        this.monde = world;
        monde.ajouterObservateur(this);

        addActi = new Button("Ajouter Activité");
        addActi.setStyle("-fx-border-color: BLACK");
        addActi.setStyle("-fx-background-color: rgba(3,84,176,0.07)");
        addActi.setMaxSize(300,300);
        addActi.setFont(new Font(30));
        addActi.setOnAction(actionEvent -> {
            monde.ajouter("Activite");
            monde.deselectionnerTout();
            monde.notifierObservateur();
        });

        addGuichet = new Button("Ajouter Guichet");
        addGuichet.setStyle("-fx-border-color: BLACK");
        addGuichet.setStyle("-fx-background-color: rgba(3,84,176,0.07)");
        addGuichet.setMaxSize(300,300);
        addGuichet.setFont(new Font(30));
        addGuichet.setOnAction(actionEvent -> {
            monde.ajouter("Guichet");
            monde.deselectionnerTout();
            monde.notifierObservateur();
        });

        this.getChildren().addAll(addActi, addGuichet);
    }



    @Override
    public void reagir() {
        Tooltip tooltip = new Tooltip();
        StringBuilder acts = new StringBuilder();
        for (EtapeIG etape: monde
             ) {
            acts.append(etape);
            acts.append("\n");
        }
        //System.out.println(monde.toString());
        tooltip.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        Label label = new Label(acts.toString());
        tooltip.setGraphic(label);
        addActi.setTooltip(tooltip);


    }
}
