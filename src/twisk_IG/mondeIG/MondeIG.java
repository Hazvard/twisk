package twisk_IG.mondeIG;

import twisk_IG.exceptions.TwiskException;
import twisk_IG.outils.FabriqueIdentifiant;
import twisk_IG.outils.TailleComposants;
import twisk_IG.vues.Observateur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MondeIG implements Iterable<EtapeIG> {

    private HashMap<String , EtapeIG> etapeIGs;
    private ArrayList<Observateur> observateurs;
    private ArrayList<ArcIG> arcs;
    private HashMap<String, EtapeIG> selection;
    private HashMap<String, ArcIG> selectionArcs;
    private PointDeControleIG ptsSauv;

    public MondeIG() {

        TailleComposants taille = TailleComposants.getInstance();

        etapeIGs = new HashMap<String, EtapeIG>();
        etapeIGs.put("0", new ActiviteIG("Activité initiale", "0", taille.getHauteurActivite(), taille.getLargeurActivite()));
        observateurs = new ArrayList<Observateur>();
        arcs = new ArrayList<>();
        selectionArcs = new HashMap<>();
        selection = new HashMap<>();
        ptsSauv = null;

    }

    public void ajouter(String type){

        TailleComposants taille = TailleComposants.getInstance();

        if(type.equals("Activité")){
            FabriqueIdentifiant fabriqueIdentifiant = FabriqueIdentifiant.getInstance();
            String identifiant = fabriqueIdentifiant.getIdentifiant();
            etapeIGs.put(identifiant, new ActiviteIG("Activité " + identifiant, identifiant, taille.getHauteurActivite(), taille.getLargeurActivite()));
        }else if(type.equals("Guichet")){

        }
        reagir();

    }

    public void selection(EtapeIG activiteIG){

        if(activiteIG.estUneActivite()) {

            if (selection.containsKey(activiteIG.getIdentifiant())) {
                selection.remove(activiteIG.getIdentifiant());
            } else {
                selection.put(activiteIG.getIdentifiant(), activiteIG);

            }
        }

    }

    public void selection(ArcIG arc){

        if (selectionArcs.containsKey(arc.getIdentifiant())) {
            selectionArcs.remove(arc.getIdentifiant());
        } else {
            selectionArcs.put(arc.getIdentifiant(), arc);

        }
    }





    public void ajouter(PointDeControleIG pt1, PointDeControleIG pt2){
        arcs.add(new ArcIG(pt1, pt2));
        reagir();

    }

    public void reagir(){
        resetSelection();
        ptsSauv = null;
        for (Observateur observateur: this.observateurs)
            observateur.reagir();
    }

    public void ajouterObservateur(Observateur observateur){
        observateurs.add(observateur);
    }


    public HashMap<String, EtapeIG> getEtapeIGs() {
        return etapeIGs;
    }

    public EtapeIG getEtapeIG(String clef){
        return etapeIGs.get(clef);
    }

    public HashMap<String, EtapeIG> getSelection() {
        return selection;
    }

    public Iterator<EtapeIG> iterator() {
        return etapeIGs.values().iterator();
    }

    public Iterator<ArcIG> arcIGIterator(){
        return arcs.iterator();
    }

    public Iterator<EtapeIG> selectionIterator(){
        return selection.values().iterator();
    }

    public Iterator<ArcIG> selectionArcIterator(){
        return selectionArcs.values().iterator();
    }

    public ArrayList<ArcIG> getArcs() {
        return arcs;
    }

    public void relierDeuxPoints(PointDeControleIG point) throws TwiskException {
        if(ptsSauv == null){
            ptsSauv = point;
        }else if(ptsSauv.getIdentifiant() == point.getIdentifiant()){
            throw new TwiskException("Erreur de connection");

        }else if(ptsSauv.getPosX() != point.getPosX() || ptsSauv.getPosY() != point.getPosY()){
            ajouter(ptsSauv, point);
            ptsSauv = null;

        }else {
            throw new TwiskException("Erreur de connection");
        }
    }

    public void supprimer(){

        ArrayList<ArcIG> arcRetirer = new ArrayList<>();

        for (Iterator<ArcIG> arcSelect = selectionArcIterator(); arcSelect.hasNext();){
            ArcIG arcIG = arcSelect.next();

            arcRetirer = new ArrayList<>();

            for (int i = 0; i < arcs.size(); i++) {
                if (arcs.get(i).getIdentifiant() == arcIG.getIdentifiant() || arcs.get(i).getIdentifiant() == arcIG.getIdentifiant()) {
                    arcRetirer.add(arcs.get(i));
                }
            }
            for (int j = 0; j < arcRetirer.size(); j++){
                arcs.remove(arcRetirer.get(j));
            }

        }

        Iterator<EtapeIG> etapes = selectionIterator();
        for (Iterator<EtapeIG> it = etapes; it.hasNext(); ){
            EtapeIG etape = it.next();


            arcRetirer = new ArrayList<>();

            for (int i = 0; i < arcs.size(); i++) {
                if (arcs.get(i).getDepart().getIdentifiant() == etape.getIdentifiant() || arcs.get(i).getFin().getIdentifiant() == etape.getIdentifiant()) {
                    arcRetirer.add(arcs.get(i));
                }
            }
            for (int j = 0; j < arcRetirer.size(); j++){
                arcs.remove(arcRetirer.get(j));
            }


            etapeIGs.remove(etape.getIdentifiant());
        }
        reagir();
    }

    public PointDeControleIG getPtsSauv() {
        return ptsSauv;
    }

    public void resetSelection(){
        selection = new HashMap();
        selectionArcs = new HashMap<>();
    }

    public boolean unElementSelection(){
        return selection.size() == 1;
    }

    public void Renomer(String nom){
        if(unElementSelection()){
            for (Iterator<EtapeIG> it = selectionIterator(); it.hasNext(); ){
                it.next().setNom(nom);
            }
        }
        reagir();
    }

    public void nullPtsControle(){
        ptsSauv = null;
    }

    public void modifierCoordonneesEtape(String identifiant, int posX, int posY){
        EtapeIG nouvelleposition = etapeIGs.get(identifiant);
        nouvelleposition.setPos(posX, posY);

        for (int i = 0; i < arcs.size(); i++) {

            // Point d'arrivé
            if (arcs.get(i).getIDFin().equals(identifiant)) {
                if (arcs.get(i).getIDB().equals("haut")) {
                    arcs.get(i).setPointB(nouvelleposition.getHaut());
                } else if (arcs.get(i).getIDB().equals("bas")) {
                    arcs.get(i).setPointB(nouvelleposition.getBas());
                } else if (arcs.get(i).getIDB().equals("gauche")) {
                    arcs.get(i).setPointB(nouvelleposition.getGauche());
                } else if (arcs.get(i).getIDB().equals("droite")) {
                    arcs.get(i).setPointB(nouvelleposition.getDroite());
                }
            }
            // Point de départ
            if (arcs.get(i).getIDDepart().equals(identifiant)) {
                if (arcs.get(i).getIDA().equals("haut")) {
                    arcs.get(i).setPointA(nouvelleposition.getHaut());
                } else if (arcs.get(i).getIDA().equals("bas")) {
                    arcs.get(i).setPointA(nouvelleposition.getBas());
                } else if (arcs.get(i).getIDA().equals("gauche")) {
                    arcs.get(i).setPointA(nouvelleposition.getGauche());
                } else if (arcs.get(i).getIDA().equals("droite")) {
                    arcs.get(i).setPointA(nouvelleposition.getDroite());
                }
            }
        }

        etapeIGs.remove(identifiant);
        etapeIGs.put(identifiant, nouvelleposition);
        reagir();
    }


    public void ChangementDeParamètres(int delai, int ecart){
        if(unElementSelection()){
            for (Iterator<EtapeIG> it = selectionIterator(); it.hasNext(); ){
                it.next().setParametres(delai, ecart);
            }
        }
        reagir();
    }

    public void ajouterRetirerSortie(){
        for (Iterator<EtapeIG> etapeIG = selectionIterator(); etapeIG.hasNext(); ){
            EtapeIG etape = etapeIG.next();
            if(etape.isSortie()){
                this.etapeIGs.get(etape.getIdentifiant()).setSortie(false);
            }else{
                this.etapeIGs.get(etape.getIdentifiant()).setSortie(true);
            }
        }
        reagir();
    }

    public void ajouterRetirerEntree(){
        for (Iterator<EtapeIG> etapeIG = selectionIterator(); etapeIG.hasNext(); ){
            EtapeIG etape = etapeIG.next();
            if(etape.isEntree()){
                this.etapeIGs.get(etape.getIdentifiant()).setEntree(false);
            }else{
                this.etapeIGs.get(etape.getIdentifiant()).setEntree(true);
            }
        }
        reagir();
    }


}
