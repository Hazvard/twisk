package twiskIG.vues;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import twiskIG.exceptions.MondeException;
import twiskIG.mondeIG.EtapeIG;
import twiskIG.mondeIG.MondeIG;

public class VueOutils extends TilePane implements Observateur{
    private MondeIG monde;
    private Button addActi;
    private Button addGuichet;
    private Button simulation;

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

        simulation = new Button();
        simulation.setStyle("-fx-border-color: BLACK");
        simulation.setStyle("-fx-background-color: rgba(3,84,176,0.07)");
        simulation.setMaxSize(300,300);
        Image img = new Image("/images/play.png") ;
        ImageView view = new ImageView(img);
        view.setFitHeight(25);
        view.setPreserveRatio(true);
        simulation.setGraphic(view);
        simulation.setOnAction(actionEvent -> {
            try{
                monde.simuler();
            }catch(MondeException e){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("ATTENTION");
                alert.setHeaderText("Impossible ");
                alert.setContentText(e.getText());
                alert.showAndWait();
            }

        });

        this.getChildren().addAll(simulation, addActi, addGuichet);
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
