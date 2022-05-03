package twisk_IG;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import twisk_IG.mondeIG.MondeIG;
import twisk_IG.outils.TailleComposants;
import twisk_IG.vues.VueMenu;
import twisk_IG.vues.VueMondeIG;
import twisk_IG.vues.VueOutils;


public class MainTwisk extends Application {

        @Override
        public void start (Stage primaryStage) {

            TailleComposants tailleComposants = TailleComposants.getInstance();



            MondeIG mondeIG = new MondeIG();

            BorderPane root = new BorderPane() ;
            root.setBottom(new VueOutils(mondeIG));
            root.setCenter(new VueMondeIG(mondeIG));
            root.setTop(new VueMenu(mondeIG));
            root.setStyle("-fx-background-color: #606668");
            primaryStage.setScene(new Scene(root, tailleComposants.getLargeurEcran(), tailleComposants.getHauteurEcran()));
            primaryStage.setTitle("Twisk");
            //primaryStage.getIcons().add(new Image("/twisk.png"));
            primaryStage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
}
