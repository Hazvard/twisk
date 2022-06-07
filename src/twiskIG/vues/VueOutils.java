package twiskIG.vues;

import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import twiskIG.exceptions.MondeException;
import twiskIG.mondeIG.EtapeIG;
import twiskIG.mondeIG.MondeIG;
import twiskIG.outils.ThreadManager;

public class VueOutils extends TilePane implements Observateur{
    private MondeIG monde;
    private Button addActi;
    private Button addGuichet;
    private Button simulation;
    private Button arreterSimulation;

    /**
     * Constructeur
     * @param world
     */
    public VueOutils(MondeIG world){
        this.monde = world;
        monde.ajouterObservateur(this);

        addActi = new Button();
        Image act = new Image("/images/act.png") ;
        ImageView viewact = new ImageView(act);
        viewact.setFitHeight(25);
        viewact.setPreserveRatio(true);
        addActi.setGraphic(viewact);
        addActi.setStyle("-fx-border-color: BLACK");
        addActi.setStyle("-fx-background-color: rgba(3,84,176,0.07)");
        addActi.setMaxSize(275,300);
        addActi.setFont(new Font(27));
        addActi.setOnAction(actionEvent -> {
            monde.ajouter("Activite");
            monde.deselectionnerTout();
            monde.notifierObservateur();
        });

        addGuichet = new Button();
        Image gch = new Image("/images/gch.png") ;
        ImageView viewgch = new ImageView(gch);
        viewgch.setFitHeight(25);
        viewgch.setPreserveRatio(true);
        addGuichet.setGraphic(viewgch);
        addGuichet.setStyle("-fx-border-color: BLACK");
        addGuichet.setStyle("-fx-background-color: rgba(3,84,176,0.07)");
        addGuichet.setMaxSize(275,300);
        addGuichet.setFont(new Font(27));
        addGuichet.setOnAction(actionEvent -> {
            monde.ajouter("Guichet");
            monde.deselectionnerTout();
            monde.notifierObservateur();
        });

        simulation = new Button();
        simulation.setStyle("-fx-border-color: BLACK");
        simulation.setStyle("-fx-background-color: rgba(3,84,176,0.07)");
        simulation.setMaxSize(275,300);
        Image img = new Image("/images/play.png") ;
        ImageView view = new ImageView(img);
        view.setFitHeight(25);
        view.setPreserveRatio(true);
        simulation.setGraphic(view);
        simulation.setOnAction(actionEvent -> {
            this.simuEnThread();
        });

        arreterSimulation = new Button();
        arreterSimulation.setStyle("-fx-border-color: BLACK");
        arreterSimulation.setStyle("-fx-background-color: rgba(3,84,176,0.07)");
        arreterSimulation.setMaxSize(275,300);
        Image imgStop = new Image("/images/stop.png");
        ImageView viewStop = new ImageView(imgStop);
        viewStop.setFitHeight(25);
        viewStop.setPreserveRatio(true);
        arreterSimulation.setGraphic(viewStop);
        arreterSimulation.setOnAction(actionEvent -> {
            //ThreadManager threadManager = ThreadManager.getInstance();
            //threadManager.detruireTout();
        });




        this.getChildren().addAll(simulation, arreterSimulation, addActi, addGuichet);

        Tooltip tooltip = new Tooltip();
        tooltip.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        Label label = new Label("Ajouter une activité");
        tooltip.setGraphic(label);
        addActi.setTooltip(tooltip);

        Tooltip tooltip1 = new Tooltip();
        tooltip.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        Label label1 = new Label("Ajouter un Guichet");
        tooltip1.setGraphic(label1);
        addGuichet.setTooltip(tooltip1);

        Tooltip tooltip2 = new Tooltip();
        tooltip.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        Label label2 = new Label("Stoper la simulation");
        tooltip2.setGraphic(label2);
        arreterSimulation.setTooltip(tooltip2);

        Tooltip tooltip3 = new Tooltip();
        tooltip.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        Label label3 = new Label("Jouer la simulation");
        tooltip3.setGraphic(label3);
        simulation.setTooltip(tooltip3);
    }

    /**
     * Simulation en thread
     */
    public void simuEnThread(){
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try{
                    monde.simuler();
                } catch (MondeException e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("ATTENTION");
                    alert.setHeaderText("Impossible ");
                    alert.setContentText(e.getText());
                    alert.showAndWait();
                    throw new RuntimeException(e);
                }
                return null;
            }
        };
        ThreadManager threadManager = ThreadManager.getInstance();
        threadManager.lancer(task);
    }
    /**
     * Permet de mettre à jour les vues
     */
    @Override
    public void reagir() {


    }
}
