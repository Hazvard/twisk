package twisk_IG.vues;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import twisk_IG.mondeIG.EtapeIG;
import twisk_IG.mondeIG.MondeIG;

import java.util.Iterator;
import java.util.Optional;

public class VueMenu extends MenuBar {

    private MondeIG mondeIG;
    private Menu fichier;
    private Menu edition;
    private Menu monde;
    private MenuItem parametre;
    private MenuItem quitter;
    private MenuItem supprimer;
    private MenuItem renommer;
    private MenuItem effaceSelection;
    private MenuItem entree;
    private MenuItem sortie;

    private String delai;
    private String ecart;





    public VueMenu(MondeIG mondeIG){
        this.mondeIG = mondeIG;

        fichier = new Menu("Fichier");
        edition = new Menu("Edition");
        monde = new Menu("Monde");
        parametre = new MenuItem("Paramètres");

        quitter = new MenuItem("Quitter");
        supprimer = new MenuItem("Supprimer");
        renommer = new MenuItem("Renommer");
        effaceSelection = new MenuItem("Effacer la sélection");
        entree = new MenuItem("Entrée");
        sortie = new MenuItem("Sortie");


        parametre.setOnAction(event -> {
            if(mondeIG.unElementSelection()){

                Iterator<EtapeIG> ite = mondeIG.selectionIterator();
                EtapeIG etapeIG = ite.next();

                Stage boiteDeDialogue = new Stage();
                boiteDeDialogue.initModality(Modality.APPLICATION_MODAL);

                TextField text1 = new TextField(String.valueOf(etapeIG.getDelai()));
                TextField text2 = new TextField(String.valueOf(etapeIG.getEcart()));



                Button button = new Button("Soumettre");
                button.setOnAction(e -> {
                    delai = text1.getText();
                    ecart = text2.getText();

                    boiteDeDialogue.close();

                });

                Label label1 = new Label("Paramètres d'une Activité");
                Label label2 = new Label("Délai : ");
                Label label3 = new Label("Ecart : ");

                GridPane layout = new GridPane();

                layout.setPadding(new Insets(10, 10, 10, 10));
                layout.setVgap(5);
                layout.setHgap(5);

                layout.add(text1, 1,1);
                layout.add(text2, 1,2);
                layout.add(button, 1,3);
                layout.add(label1, 1,0);
                layout.add(label2, 0,1);
                layout.add(label3, 0,2);

                Scene scene = new Scene(layout, 250, 150);
                boiteDeDialogue.setTitle("Paramètres");
                boiteDeDialogue.setScene(scene);
                boiteDeDialogue.showAndWait();

                try {
                    int delaival = Integer.parseInt(delai);
                    int ecartval = Integer.parseInt(ecart);
                    mondeIG.ChangementDeParamètres(delaival, ecartval);
                } catch (NumberFormatException e){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Valeur erroné");
                    alert.setHeaderText("Au moins une des valeurs rentrées est incorrecte, Vous devez rentrer uniquement des entiers.");
                    alert.setContentText("Les paramètres ne seront pas modifiés.");

                    alert.show();
                    PauseTransition pause = new PauseTransition(Duration.seconds(5));
                    pause.setOnFinished(n -> alert.close() );
                    pause.play();
                }

            }
        });

        entree.setOnAction((event -> {
            mondeIG.ajouterRetirerEntree();
        }));

        sortie.setOnAction((event -> {
            mondeIG.ajouterRetirerSortie();
        }));

        quitter.setOnAction(event -> {
            Platform.exit();
        });

        supprimer.setOnAction(event -> {
            mondeIG.supprimer();

        });

        renommer.setOnAction(event -> {
            if(mondeIG.unElementSelection()){
                TextInputDialog dialog = new TextInputDialog("Activité");

                dialog.setTitle("Définir le nom de l'Activité");
                dialog.setHeaderText("Entrer le nom de l'activité :");
                dialog.setContentText("Nom :");

                Optional<String> result = dialog.showAndWait();

                result.ifPresent(name -> {
                    this.mondeIG.Renomer(name);
                });
            }

        });

        effaceSelection.setOnAction(event -> {
            mondeIG.resetSelection();
            mondeIG.reagir();
        });

        fichier.getItems().addAll(quitter);
        edition.getItems().addAll(effaceSelection,renommer, parametre, supprimer);
        monde.getItems().addAll(entree, sortie);


        this.getMenus().addAll(fichier , edition, monde) ;

    }


}
