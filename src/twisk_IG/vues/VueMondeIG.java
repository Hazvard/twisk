package twisk_IG.vues;

import javafx.scene.control.Label;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import twisk_IG.mondeIG.ArcIG;
import twisk_IG.mondeIG.EtapeIG;
import twisk_IG.mondeIG.MondeIG;
import twisk_IG.mondeIG.PointDeControleIG;

import java.util.Iterator;

public class VueMondeIG extends Pane implements Observateur{

    private MondeIG mondeIG;

    public VueMondeIG(MondeIG mondeIG){
        this.mondeIG = mondeIG ;
        this.mondeIG.ajouterObservateur(this);
        for (EtapeIG etape: mondeIG) {
            this.getChildren().addAll(new VueActiviteIG(mondeIG, etape));
            for (PointDeControleIG pointDeControleIG: etape)
                this.getChildren().add(new VuePointDeControle(mondeIG, etape, pointDeControleIG ));
        }

        this.setOnDragOver(event->{
            event.acceptTransferModes(TransferMode.MOVE);
            event.consume();
        });
        this.setOnDragDropped(event->{
            boolean success = false;
            try {
                final Dragboard db = event.getDragboard();
                String id = db.getString();
                EtapeIG e = this.mondeIG.getEtapeIG(id);
                this.mondeIG.modifierCoordonneesEtape(id,(int)event.getX()-e.getLargeur()/2,(int)event.getY()-e.getHauteur()/2);
                success = true;
            } catch(Exception e){
                System.out.println(e.toString());
            }finally {
                event.setDropCompleted(success);
                event.consume();
            }
        });

    }


    @Override
    public void reagir() {
        this.getChildren().clear();

        Iterator<ArcIG> arcs = mondeIG.arcIGIterator();
        for (Iterator<ArcIG> it = arcs; it.hasNext(); )
            this.getChildren().add(new VueArcIG(mondeIG, it.next()));

        for (EtapeIG etape: mondeIG) {
            this.getChildren().addAll(new VueActiviteIG(mondeIG, etape));
            for (PointDeControleIG pointDeControleIG: etape)
                this.getChildren().add(new VuePointDeControle(mondeIG, etape, pointDeControleIG ));
        }
    }
}
