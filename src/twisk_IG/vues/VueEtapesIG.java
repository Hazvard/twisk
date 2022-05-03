package twisk_IG.vues;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import twisk_IG.mondeIG.EtapeIG;
import twisk_IG.mondeIG.MondeIG;

import static javafx.scene.input.TransferMode.*;


public abstract class VueEtapesIG extends VBox implements Observateur {

    protected EtapeIG etapeIG;
    protected MondeIG mondeIG;
    protected Label label;


    public VueEtapesIG(MondeIG mondeIG, EtapeIG etapeIG){
        this.mondeIG = mondeIG;

        this.etapeIG = etapeIG;
        label = new Label(etapeIG.getNom());
        label.setStyle("-fx-font: 15 Impact ; -fx-text-fill: #C91003; -fx-background-color: #BEC2BE ; -fx-alignment: center ; -fx-text-alignment: center");
        label.setAlignment(Pos.TOP_CENTER);

        entreSorite();


        this.setMinSize(etapeIG.getLargeur(), etapeIG.getHauteur());
        this.setMaxSize(etapeIG.getLargeur(), etapeIG.getHauteur());
        this.setStyle("-fx-background-color: #BEC2BE ; -fx-background-radius: 10 ; -fx-border-radius: 10 ; -fx-border-width: 2 ; -fx-border-color: #000000");
        this.getChildren().add(label);
        this.setAlignment(Pos.CENTER);



    }

    public VueEtapesIG(MondeIG mondeIG, EtapeIG etapeIG, Label label1){
        this.mondeIG = mondeIG;

        this.etapeIG = etapeIG;
        this.label =label1;
        label.setStyle("-fx-font: 15 Impact ; -fx-text-fill: #C91003; -fx-background-color: #BEC2BE ; -fx-alignment: center ; -fx-text-alignment: center");
        label.setAlignment(Pos.TOP_CENTER);


        entreSorite();



        this.setMinSize(etapeIG.getLargeur(), etapeIG.getHauteur());
        this.setMaxSize(etapeIG.getLargeur(), etapeIG.getHauteur());
        this.setStyle("-fx-background-color: #BEC2BE ; -fx-background-radius: 10 ; -fx-border-radius: 10 ; -fx-border-width: 2 ; -fx-border-color: #000000");
        this.getChildren().add(label);
        this.setAlignment(Pos.CENTER);

        this.setOnDragDetected(event->{
            Dragboard drag = this.startDragAndDrop(MOVE);
            ClipboardContent content = new ClipboardContent();
            WritableImage image = this.snapshot(null, null);
            content.putImage(image);
            content.putString(this.etapeIG.getIdentifiant());
            drag.setContent(content);
            event.consume();
        });



    }

    public void setLabel(Label label1) {
        this.getChildren().clear();


        this.label = label1;
        label.setStyle("-fx-font: 15 Impact ; -fx-text-fill: #C91003; -fx-background-color: #BEC2BE ; -fx-alignment: center ; -fx-text-alignment: center");
        label.setAlignment(Pos.TOP_CENTER);

        entreSorite();


        this.setMinSize(etapeIG.getLargeur(), etapeIG.getHauteur());
        this.setMaxSize(etapeIG.getLargeur(), etapeIG.getHauteur());
        this.setStyle("-fx-background-color: #BEC2BE ; -fx-background-radius: 10 ; -fx-border-radius: 10 ; -fx-border-width: 2 ; -fx-border-color: #000000");
        this.getChildren().add(label);
        this.setAlignment(Pos.CENTER);
    }

    public void selection(Label label1){

        this.getChildren().clear();

        setLabel(label1);


        label.setStyle("-fx-font: 15 Impact ; -fx-text-fill: #FC3022; -fx-background-color: #BEC2BE ; -fx-alignment: center ; -fx-text-alignment: center");
        label.setAlignment(Pos.TOP_CENTER);

        entreSorite();


        this.setMinSize(etapeIG.getLargeur(), etapeIG.getHauteur());
        this.setMaxSize(etapeIG.getLargeur(), etapeIG.getHauteur());
        this.setStyle("-fx-background-color: #BEC2BE ; -fx-background-radius: 10 ; -fx-border-radius: 10 ; -fx-border-width: 2 ; -fx-border-color: #C91003");
        this.setAlignment(Pos.CENTER);

    }

    public void nonSelection(Label label1){
        this.getChildren().clear();

        setLabel(label1);

        label.setStyle("-fx-font: 15 Impact ; -fx-text-fill: #C91003; -fx-background-color: #BEC2BE ; -fx-alignment: center ; -fx-text-alignment: center");
        label.setAlignment(Pos.TOP_CENTER);

        entreSorite();

        this.setMinSize(etapeIG.getLargeur(), etapeIG.getHauteur());
        this.setMaxSize(etapeIG.getLargeur(), etapeIG.getHauteur());
        this.setStyle("-fx-background-color: #BEC2BE ; -fx-background-radius: 10 ; -fx-border-radius: 10 ; -fx-border-width: 2 ; -fx-border-color: #000000");

        this.setAlignment(Pos.CENTER);

    }

    private void entreSorite(){
        if(this.etapeIG.isEntree() && this.etapeIG.isSortie()){
            final Image entree = new Image("/entreeSortie.png");
            final ImageView iconE = new ImageView(entree);
            label.setGraphic(iconE);
            label.setContentDisplay(ContentDisplay.RIGHT);
        }
        if(this.etapeIG.isEntree() && !this.etapeIG.isSortie()){
            final Image entree = new Image("/entree.png");
            final ImageView iconE = new ImageView(entree);
            label.setGraphic(iconE);
            label.setContentDisplay(ContentDisplay.RIGHT);
        }
        if(this.etapeIG.isSortie() && !this.etapeIG.isEntree()){
            final Image sortie = new Image("/sortie.png");
            final ImageView iconS = new ImageView(sortie);
            label.setGraphic(iconS);
            label.setContentDisplay(ContentDisplay.RIGHT);
        }
    }


}
