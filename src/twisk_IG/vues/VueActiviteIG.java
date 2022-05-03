package twisk_IG.vues;


import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import twisk_IG.mondeIG.EtapeIG;
import twisk_IG.mondeIG.MondeIG;
import twisk_IG.outils.TailleComposants;


public class VueActiviteIG extends VueEtapesIG{

    private Label label;
    private Boolean selection;

    public VueActiviteIG(MondeIG mondeIG, EtapeIG etapeIG) {

        super(mondeIG, etapeIG, new Label(etapeIG.getNom() + " : " + etapeIG.getDelai() + " ± " + etapeIG.getEcart()));

        TailleComposants taille = TailleComposants.getInstance();
        selection = false;


        HBox hBox = new HBox();
        hBox.setStyle("-fx-border-color: #C91003; -fx-background-radius: 2; -fx-border-width: 3 ; -fx-border-radius: 2 ; -fx-background-color: #FFFFFF");
        hBox.setMaxSize(taille.getLargeurBoxAct(), taille.getHauteurBoxAct());
        hBox.setMinSize(taille.getLargeurBoxAct(), taille.getHauteurBoxAct());

        this.getChildren().addAll(hBox);
        this.relocate(etapeIG.getPosX(), etapeIG.getPosY());

        this.setOnMouseClicked(event ->{
            mondeIG.selection(this.etapeIG);

            this.getChildren().clear();

            if(selection){
                selection = false;

                nonSelection(new Label(etapeIG.getNom() + " : " + etapeIG.getDelai() + " ± " + etapeIG.getEcart()));

                hBox.setStyle("-fx-border-color: #C91003; -fx-background-radius: 2; -fx-border-width: 3 ; -fx-border-radius: 2 ; -fx-background-color: #FFFFFF");
                hBox.setMaxSize(taille.getLargeurBoxAct(), taille.getHauteurBoxAct());
                hBox.setMinSize(taille.getLargeurBoxAct(), taille.getHauteurBoxAct());



                this.getChildren().addAll(hBox);
                this.relocate(etapeIG.getPosX(), etapeIG.getPosY());


            }else {
                selection = true;

                selection(new Label(etapeIG.getNom() + " : " + etapeIG.getDelai() + " ± " + etapeIG.getEcart()));

                hBox.setStyle("-fx-border-color: #FC3022; -fx-background-radius: 2; -fx-border-width: 3 ; -fx-border-radius: 2 ; -fx-background-color: #FFFFFF");
                hBox.setMaxSize(taille.getLargeurBoxAct(), taille.getHauteurBoxAct());
                hBox.setMinSize(taille.getLargeurBoxAct(), taille.getHauteurBoxAct());



                this.getChildren().addAll(hBox);
                this.relocate(etapeIG.getPosX(), etapeIG.getPosY());

            }

        });


    }



    @Override
    public void reagir() {

    }
}
