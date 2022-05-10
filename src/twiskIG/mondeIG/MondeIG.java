package twiskIG.mondeIG;

import javafx.scene.control.Alert;
import twiskIG.exceptions.TwiskException;
import twiskIG.outils.FabriqueIdentifiant;
import twiskIG.outils.TailleComposant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MondeIG extends SujetObserve implements Iterable<EtapeIG>{
    private HashMap<String, EtapeIG> etapeIG;
    private ArrayList<ArcIG> arcIGs;
    private int balisePdc;
    private PointDeControlIG pointTempo;
    private ArrayList<String> idEtapeSelect;
    private ArrayList<ArcIG> arcSelect;
    private ArrayList<EtapeIG> entrees;
    private ArrayList<EtapeIG> sorties;

    public MondeIG(){
        arcSelect = new ArrayList<>();
        arcIGs = new ArrayList<>();
        entrees = new ArrayList<>();
        sorties = new ArrayList<>();
        etapeIG = new HashMap<String, EtapeIG>();
        balisePdc = 0;
        pointTempo = new PointDeControlIG(new ActiviteIG("EtapeTempo","id",1,1));
        idEtapeSelect = new ArrayList<>();
    }

    public void ajouter(String type){
        if(type == "Activite"){
            FabriqueIdentifiant fab = FabriqueIdentifiant.getInstance();
            TailleComposant t = TailleComposant.getInstance();
            String tmp = fab.getIdentiantEtape();
            etapeIG.put(tmp, new ActiviteIG(type,tmp,t.getHauteurEtape(),t.getLargeurEtape()));
        }
        else if(type == "Guichet"){
            FabriqueIdentifiant fab = FabriqueIdentifiant.getInstance();
            TailleComposant t = TailleComposant.getInstance();
            String tmp = fab.getIdentiantEtape();
            etapeIG.put(tmp, new GuichetIG(type,tmp,t.getHauteurEtape(),t.getLargeurEtape(), 2));
        }
    }

    public void changerJetons(int nbJeton) throws TwiskException {
        if(idEtapeSelect.size() == 0){
            throw new TwiskException("Cliquez sur un guicher pour changer son nombre de jetons");
        }
        for (String id: idEtapeSelect
             ) {
            if(etapeIG.get(id).isUnGuichet()){
                etapeIG.get(id).changerNbJeton(nbJeton);
            }
            else{throw new TwiskException("Seul les guichets possèdent des jetons à donner");}
        }
    }

    public void ajouterEntrees() throws TwiskException{
        for (String id: idEtapeSelect
             ) {
            if(!etapeIG.get(id).isUneEntree() && !etapeIG.get(id).isUneSortie()){
                etapeIG.get(id).setEtapeEntree(true);
                entrees.add(etapeIG.get(id));
            }
            else if(etapeIG.get(id).isUneSortie()){
                throw new TwiskException("Une activité est déjà une sortie !!");
            }
            else{
                etapeIG.get(id).setEtapeEntree(false);
                entrees.remove(etapeIG.get(id));
            }
        }
    }

    public void ajouterSortie() throws TwiskException{
        for (String id: idEtapeSelect
        ) {
            if(etapeIG.get(id).isUnGuichet()){
                throw new TwiskException("Un guichet ne peut pas être sortie");
            }
            else if(!etapeIG.get(id).isUneEntree() && !etapeIG.get(id).isUneSortie()){
                etapeIG.get(id).setEstUneSortie(true);
                sorties.add(etapeIG.get(id));
            }
            else if(etapeIG.get(id).isUneEntree()){
                throw new TwiskException("Une activité est déjà une entrée !!");
            }
            else{
                etapeIG.get(id).setEstUneSortie(false);
                sorties.remove(etapeIG.get(id));
            }
        }
    }

    public int getTailleArc(){
        return arcIGs.size();
    }

    public void ajouter(PointDeControlIG p1, PointDeControlIG p2){
        arcIGs.add(new ArcIG(p1, p2));
        //System.out.println("Ajout d'un arc...");
    }

    public EtapeIG getEtape(String id){
        return etapeIG.get(id);
    }

    public void modifCoordAct(String etape, int newX, int newY){
        EtapeIG etapeTempo = etapeIG.get(etape);
        etapeTempo.setPosX(newX);
        etapeTempo.setPosY(newY);
        etapeTempo.repositionnerPDC();
        etapeIG.remove(etape);
        etapeIG.put(etapeTempo.getIdentifiant(),etapeTempo);
        this.notifierObservateur();
    }

    public void modifDelaiEcart(int delai, int ecart) throws TwiskException{
        if(idEtapeSelect.size() == 0){
            throw new TwiskException("Cliquez sur une activité pour modifier son écart/temps");
        }
        else if (delai > ecart) {
            for (String id : idEtapeSelect
            ) {
                if(etapeIG.get(id).isUnGuichet()){
                    throw new TwiskException("Un guichet ne possède pas de délai");
                }
                etapeIG.get(id).setDelai(delai);
                etapeIG.get(id).setEcart(ecart);
            }
        } else {
            throw new TwiskException("L'écart doit être inférieure au délai");
        }
    }

    public void deselectionnerTout(){
        for (ArcIG arc: arcSelect
             ) {
            arc.deselectionnerArc();
        }
        for (String id: idEtapeSelect
             ) {
            etapeIG.get(id).deselectionnerEtape();
        }
        arcSelect = new ArrayList<>();
        idEtapeSelect = new ArrayList<>();
    }

    public void pointDeControleCliquer(PointDeControlIG point) throws TwiskException {

        if(balisePdc == 1 && pointTempo.getEtape().getIdentifiant() != point.getEtape().getIdentifiant()){
            arcIGs.add(new ArcIG(pointTempo, point));
            //System.out.println("Ajout d'un arc");
            this.resetBalise();
            pointTempo.deselectionner();
            point.deselectionner();
        }
        else if(balisePdc == 1 && point.estDeLaMemeEtape(pointTempo.getEtape())){
            this.resetBalise();
            point.deselectionner();
            pointTempo.deselectionner();
            throw new TwiskException("Essayez de ne pas relier deux points de contrôle d'une même étape ;)");
        }
        else{
            point.setEstSelec();
            this.setPointTempo(point);
            balisePdc++;
        }
    }

    public void resetBalise(){
        balisePdc = 0;
    }

    public void selectEt(EtapeIG et){
        if(!et.estSelectionnee()){
            et.selectionnerEtape();
            idEtapeSelect.add(et.getIdentifiant());
        }
        else{
            idEtapeSelect.remove(et.getIdentifiant());
            et.deselectionnerEtape();
        }
        //System.out.println("Etat de l'étape : " + et.estSelectionnee());
        //System.out.println("Liste des etapes selec : " + idEtapeSelect.toString());
    }

    public void selectArc(ArcIG arcIG){
        if(!arcIG.isSelectionner()){
            arcIG.selectionnerArc();
            arcSelect.add(arcIG);
        }
        else{
            arcSelect.remove(arcIG);
            arcIG.deselectionnerArc();
        }
    }

    public void setPointTempo(PointDeControlIG pointTempo) {
        this.pointTempo = pointTempo;
    }

    public String toString(){
        return etapeIG.values().toString() + "\n Arcs : " + arcIGs.toString();
    }

    public void supprSelection(){
        ArrayList<ArcIG> arcASuppr = new ArrayList<>();
        //Enlève tout les arcs
        for (ArcIG arc: arcIGs
             ) {
            for (EtapeIG etape: this
                 ) {
                if(etape.estSelectionnee()){
                    arcASuppr.add(arc);
                }
            }
           }
        for (ArcIG arc: arcASuppr
             ) {
            arcIGs.remove(arc);
        }
        for (ArcIG arc: arcSelect
             ) {
            arcIGs.remove(arc);
        }
        //Enlève toutes les étapes
        for(Iterator<String> ite = idEtapeSelectIterator(); ite.hasNext();){
            String etapeSuppr = ite.next();
            etapeIG.remove(etapeSuppr);
        }
        idEtapeSelect = new ArrayList<>();
    }

    public int nbEtapeSelect(){
        return idEtapeSelect.size();
    }

    @Override
    public Iterator<EtapeIG> iterator() {
        return etapeIG.values().iterator();
    }

    public Iterator<ArcIG> arcIGIterator(){
        return arcIGs.iterator();
    }

    public Iterator<String> idEtapeSelectIterator(){
        return idEtapeSelect.iterator();
    }

    public void renommerActivite(String newNom) throws TwiskException{
        if(idEtapeSelect.size() > 1){
            throw new TwiskException("On ne peut renommer qu'une seule activité à la fois !!");
        }
        else if(idEtapeSelect.size() == 0){
            throw new TwiskException("Cliquez sur une activité pour la renommer");
        }
        else {
            etapeIG.get(idEtapeSelect.get(0)).renommerEtapeIG(newNom);
        }
    }
}
