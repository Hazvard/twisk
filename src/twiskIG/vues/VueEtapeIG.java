package twiskIG.vues;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import twiskIG.mondeIG.EtapeIG;
import twiskIG.mondeIG.MondeIG;
import twiskIG.outils.TailleComposant;

import java.awt.*;

public abstract class VueEtapeIG extends VBox implements Observateur{
    private MondeIG monde;
    private EtapeIG etape;
    private Label lab;

    public VueEtapeIG(MondeIG world, EtapeIG etape){
        this.monde = world;
        this.etape = etape;
        this.setId("VueEtape");
        this.setMinSize(etape.getHauteur(),etape.getLargeur());
        this.setMaxSize(etape.getHauteur(),etape.getLargeur());
        HBox hBox = new HBox();
        Label dsBoite = new Label("");
        dsBoite.setFont(new Font(25));
        dsBoite.setAlignment(Pos.CENTER);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(dsBoite);
        if (etape.isUnGuichet()){
            this.lab = new Label(etape.getNom() + " " + etape.getNbJetons() + " jetons");

        }
        else{
            lab = new Label(etape.getNom() +" "+ etape.getIdentifiant() + ": " + etape.getDelai() + " ± " + etape.getEcart());
        }
        lab.setId("nomEtape");
        lab.setFont(new Font(25));
        this.setAlignment(Pos.CENTER);
        if(etape.estSelectionnee()){
            this.setStyle("-fx-border-color: rgb(238,6,6)");
        }
        if(etape.isUneEntree()){
            this.setStyle("-fx-border-color: rgb(0,255,20)");
            Image entree = new Image("/images/entree.png",50,50,true,true);
            ImageView smallEntree = new ImageView(entree);
            lab.setGraphic(smallEntree);
            lab.setContentDisplay(ContentDisplay.LEFT);
        }
        if(etape.isUneSortie()){
            this.setStyle("-fx-border-color: rgb(234,105,0)");
            Image entree = new Image("/images/sortie.png",50,50,true,true);
            ImageView smallSortie = new ImageView(entree);
            lab.setGraphic(smallSortie);
            lab.setContentDisplay(ContentDisplay.RIGHT);
        }
        if(etape.isUnGuichet()){
            this.setStyle("-fx-border-color: rgb(219,2,245)");
        }
        this.setLayoutX(etape.getPosX());
        this.setLayoutY(etape.getPosY());
        this.getChildren().addAll(lab,hBox);

        this.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->{
            monde.selectEt(etape);
            if(etape.estSelectionnee()){
                this.setStyle("-fx-border-color: rgb(238,6,6)");
            }
            else{
                monde.notifierObservateur();
            }
        });
        this.setOnDragDetected(event ->{
            Dragboard dnd = this.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            WritableImage imageAct = this.snapshot(null,null);
            content.putImage(imageAct);
            content.putString(etape.getIdentifiant());
            dnd.setContent(content);
            event.consume();
        });
    }

    @Override
    public void reagir(){
        lab.setText(this.etape.getNom());
    }
}
