package twiskIG.vues;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import javafx.util.Pair;
import twiskIG.exceptions.TwiskException;
import twiskIG.mondeIG.MondeIG;
import twiskIG.outils.Valider;
import twiskIG.outils.ValiderJeton;

import java.util.Optional;

public class VueMenu extends MenuBar implements Observateur {
    private MondeIG monde;
    private VueMondeIG vueMondeIG;

    private Menu fichier;
    private MenuItem quitter;

    private Menu edition;
    private MenuItem suprSelec;
    private MenuItem rename;
    private MenuItem deselectionner;

    private Menu mondeMenu;
    private MenuItem entree;
    private MenuItem sortie;

    private Menu parametre;
    private MenuItem changerEcart;
    private MenuItem changerJetons;

    public VueMenu(MondeIG world, VueMondeIG vuvue){
        this.monde = world;
        this.vueMondeIG = vuvue;
        fichier = new Menu("Fichier");
        quitter = new MenuItem("Quitter");
        quitter.setOnAction(event ->{
            Platform.exit();
        });
        fichier.getItems().add(quitter);

        edition = new Menu("Edition");
        suprSelec = new MenuItem("Supprimer Selection");
        suprSelec.setOnAction(event ->{
            monde.supprSelection();
            vueMondeIG.reagir();
        });
        rename = new MenuItem("Renommer");
        rename.setOnAction(event ->{
            TextInputDialog popRename = new TextInputDialog("Nouveau nom");
            popRename.setTitle("Renommer une Activité");
            popRename.setHeaderText("Entrez le nouveau nom de l'activité séléctionnée :");
            popRename.setContentText("Nom :");

            Optional<String> entrer = popRename.showAndWait();
            entrer.ifPresent(newNom ->{
                try {
                    monde.renommerActivite(newNom);
                    monde.deselectionnerTout();
                    monde.notifierObservateur();
                } catch (TwiskException e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("ATTENTION");
                    alert.setHeaderText("Impossible de renommer");
                    alert.setContentText(e.getText());
                    alert.showAndWait();
                }
            });
        });
        deselectionner = new MenuItem("Désélectionner tous");
        deselectionner.setOnAction(event ->{
            monde.deselectionnerTout();
            monde.notifierObservateur();
        });
        edition.getItems().addAll(suprSelec,rename,deselectionner);

        mondeMenu = new Menu("Monde");
        entree = new MenuItem("Entrée");
        entree.setOnAction(event ->{
            try {
                monde.ajouterEntrees();
                monde.deselectionnerTout();
                monde.notifierObservateur();
            } catch (TwiskException e) {
                monde.deselectionnerTout();
                monde.notifierObservateur();
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("ATTENTION");
                alert.setHeaderText("Impossible de définir Entrée");
                alert.setContentText(e.getText());
                alert.showAndWait();
            }
        });
        sortie = new MenuItem("Sortie");
        sortie.setOnAction(event -> {
            try {
                monde.ajouterSortie();
                monde.deselectionnerTout();
                monde.notifierObservateur();
            } catch (TwiskException e) {
                monde.deselectionnerTout();
                monde.notifierObservateur();
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("ATTENTION");
                alert.setHeaderText("Impossible de définir Sortie");
                alert.setContentText(e.getText());
                alert.showAndWait();
            }
        });
        mondeMenu.getItems().addAll(entree,sortie);

        parametre = new Menu("Parametres");
        changerEcart = new MenuItem("Changer Ecart/Délai");
        changerEcart.setOnAction(event -> {
            //Creation du dialogue
            Dialog<Valider> dialog = new Dialog<>();
            dialog.setTitle("Modifier Ecart et Délai");
            //Creation de ses composants
                //Creation des infos
            dialog.setHeaderText("Saisissez les nouvelles valeurs :");
            Label delai = new Label("Délai: ");
            Label ecart = new Label("Ecart");
            TextField delaiField = new TextField();
            TextField ecartField = new TextField();
            GridPane grid = new GridPane();
            grid.add(delai,1,1);
            grid.add(delaiField,2,1);
            grid.add(ecart,1,2);
            grid.add(ecartField,2,2);
            dialog.getDialogPane().setContent(grid);
                //Creation du bouton
            ButtonType validerType = new ButtonType("Valider", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(validerType);
            dialog.setResultConverter(new Callback<ButtonType, Valider>() {
                @Override
                public Valider call(ButtonType buttonType) {
                    return new Valider(delaiField.getText(), ecartField.getText());
                }
            });
            Optional<Valider> resultat = dialog.showAndWait();
            if(resultat.isPresent()){
                //System.out.println("Resultat = " + resultat.get());
                try {
                    int delaiConv = Integer.parseInt(resultat.get().getDelai());
                    int ecartConv = Integer.parseInt(resultat.get().getEcart());
                    monde.modifDelaiEcart(delaiConv, ecartConv);
                } catch (TwiskException e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("ATTENTION");
                    alert.setHeaderText("Impossible de modifier écart/délai");
                    alert.setContentText(e.getText());
                    alert.showAndWait();
                }
                  catch (NumberFormatException e){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("ATTENTION");
                    alert.setHeaderText("Impossible de modifier écart/délai");
                      alert.setContentText("Entier(s) saisi(s) incorrect(s) !!");
                      alert.showAndWait();
                  }
                monde.deselectionnerTout();
                monde.notifierObservateur();
            }
        });
        changerJetons = new MenuItem("Changer nb Jetons");
        changerJetons.setOnAction(event ->{
            //Creation du dialogue
            Dialog<Valider> dialog = new Dialog<>();
            dialog.setTitle("Modifier nb Jetons");
            //Creation de ses composants
            //Creation des infos
            dialog.setHeaderText("Saisissez les nouvelles valeurs :");
            Label jetons = new Label("Nombre de Jetons : ");
            TextField jetonField = new TextField();
            GridPane grid = new GridPane();
            grid.add(jetons,1,1);
            grid.add(jetonField,1,2);
            dialog.getDialogPane().setContent(grid);
            //Cration du bouton
            ButtonType validerType = new ButtonType("Valider", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(validerType);
            dialog.setResultConverter(new Callback<ButtonType, Valider>() {
                @Override
                public ValiderJeton call(ButtonType buttonType) {
                    return new ValiderJeton(jetonField.getText());
                }
            });
            Optional<Valider> resultat = dialog.showAndWait();
            if(resultat.isPresent()){
                //System.out.println("Resultat = " + resultat.get());
                try {
                    int newJetons = Integer.parseInt(resultat.get().getJetonValide());
                    monde.changerJetons(newJetons);
                } catch (TwiskException e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("ATTENTION");
                    alert.setHeaderText("Impossible de modifier nbJeton");
                    alert.setContentText(e.getText());
                    alert.showAndWait();
                }
                catch (NumberFormatException e){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("ATTENTION");
                    alert.setHeaderText("Impossible de modifier nbJeton");
                    alert.setContentText("Nombre saisi incorrect !!");
                    alert.showAndWait();
                }
                monde.deselectionnerTout();
                monde.notifierObservateur();
            }
        });
        parametre.getItems().addAll(changerEcart,changerJetons);


        this.getMenus().addAll(fichier, edition,mondeMenu,parametre);
    }

    @Override
    public void reagir() {
    }
}
