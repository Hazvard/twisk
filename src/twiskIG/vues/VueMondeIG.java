package twiskIG.vues;

import javafx.application.Platform;
import javafx.scene.image.WritableImage;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import twisk.monde.Etape;
import twisk.monde.GestionnaireEtape;
import twisk.simulation.Client;
import twisk.simulation.GestionnaireClients;
import twiskIG.mondeIG.ArcIG;
import twiskIG.mondeIG.CorrespondanceEtapes;
import twiskIG.mondeIG.EtapeIG;
import twiskIG.mondeIG.MondeIG;

import java.util.*;

public class VueMondeIG extends Pane implements Observateur {
    private MondeIG monde;
    private HashMap<Client,EtapeIG> hashMap;

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
        this.hashMap = new HashMap<>();
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
        //LesClients
        //1) Trouver Nb clients OUI
        //2) Trouver le nom de l'étape dans laquelles ils sont OUI
        //3) Trouver les coordonnée de l'étape en quesiton  En cours
        //4) Placer les cerles et les déplacer en concéquence si un cercle est déjà présent
        //sur l'activité...
        GestionnaireClients gestionnaireClients = monde.getGestionnaireClients();
        if (gestionnaireClients != null) {
            Iterator<Client> itClient = gestionnaireClients.iterator();
            CorrespondanceEtapes correspondanceEtapes = monde.getCorrespondanceEtapes();
            while (itClient.hasNext()) {
                Client clientTempo = itClient.next();
                Circle circle = new Circle();
                circle.setFill(javafx.scene.paint.Color.RED);
                Etape etapeTempo = clientTempo.getEtape();
                System.out.println(etapeTempo);
                EtapeIG etapeIG = correspondanceEtapes.getEtapeIG(etapeTempo);
                System.out.println(etapeIG);
                circle.setRadius(10);
                if(etapeIG != null){
                    int posX = etapeIG.getPosX() + 20;
                    int posY = etapeIG.getPosY() + 80;
                    for(Map.Entry<Client,EtapeIG> entry:hashMap.entrySet()){
                        if(Objects.equals(etapeIG, entry.getValue())){
                            posX += 35;
                        }
                    }
                    hashMap.put(clientTempo, etapeIG);
                    circle.setCenterX(posX);
                    circle.setCenterY(posY);
                    this.getChildren().add(circle);
                }
            }
        }
    }
}