package twiskIG;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import twiskIG.mondeIG.MondeIG;
import twiskIG.outils.TailleComposant;
import twiskIG.vues.VueMenu;
import twiskIG.vues.VueMondeIG;
import twiskIG.vues.VueOutils;

public class MainTwisk extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        MondeIG monde = new MondeIG();
        BorderPane root = new BorderPane();
        root.setBottom(new VueOutils(monde));
        VueMondeIG vueMonde = new VueMondeIG(monde);
        root.setCenter(vueMonde);
        root.setTop(new VueMenu(monde, vueMonde));
        stage.setTitle("Twisk");
        stage.getIcons().add(new Image("/images/twisk.png"));
        TailleComposant taille = TailleComposant.getInstance();
        root.setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(root, taille.getX(), taille.getY());
        scene.getStylesheets().add("/styles/style.css");
        stage.setScene(scene);
        stage.show();
    }

    private static void main(String[] args){launch(args);}

}
