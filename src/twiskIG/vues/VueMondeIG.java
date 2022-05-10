package twiskIG.vues;

import javafx.scene.image.WritableImage;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import twiskIG.mondeIG.ArcIG;
import twiskIG.mondeIG.EtapeIG;
import twiskIG.mondeIG.MondeIG;

import java.util.HashMap;
import java.util.Iterator;

public class VueMondeIG extends Pane implements Observateur {
    private MondeIG monde;

    public VueMondeIG(MondeIG world) {
        this.monde = world;
        monde.ajouterObservateur(this);
        this.setOnDragOver(event -> {
            event.acceptTransferModes(TransferMode.MOVE);
            event.consume();
        });
        this.setOnDragDropped(event -> {
            boolean success = false;
            try {
                final Dragboard dragboard = event.getDragboard();
                String id = dragboard.getString();
                EtapeIG etapeADrag = this.monde.getEtape(id);
                this.monde.modifCoordAct(id, (int) event.getX() - etapeADrag.getLargeur() / 2,
                        (int) event.getY() - etapeADrag.getHauteur() / 2);
                success = true;
            } catch (Exception e) {
                System.out.println(e.toString());
            } finally {
                event.setDropCompleted(success);
                event.consume();
            }
        });
    }


    @Override
    public void reagir() {
        this.getChildren().clear();

        Iterator<ArcIG> it = monde.arcIGIterator();
        ArcIG arc;
        while (it.hasNext()) {
            arc = it.next();
            this.getChildren().addAll(new VueArcIG(monde, arc));
        }
        for (EtapeIG etape : monde) {
            this.getChildren().addAll(new VueActiviteIG(monde, etape));
            if (!etape.isUnGuichet()) {
                for (int i = 0; i < 4; i++) {
                    this.getChildren().addAll(new VuePointDeControleIG(monde, etape, etape.getPdc(i)));
                }
            } else {
                for (int i = 0; i < 2; i++) {
                    this.getChildren().addAll(new VuePointDeControleIG(monde, etape, etape.getPdc(i)));
                }
            }
        }
    }
}
