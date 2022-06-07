package twiskIG.mondeIG;

import client.ClientTwisk;
import javafx.application.Platform;
import twisk.monde.*;
import twisk.outils.FabriqueNumero;
import twisk.simulation.GestionnaireClients;
import twiskIG.exceptions.MondeException;
import twiskIG.exceptions.TwiskException;
import twiskIG.outils.CorrespondanceEtapes;
import twiskIG.outils.FabriqueIdentifiant;
import twiskIG.outils.TailleComposant;
import twiskIG.vues.Observateur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MondeIG extends SujetObserve implements Iterable<EtapeIG>, Observateur {
    private HashMap<String, EtapeIG> etapeIG;
    private ArrayList<ArcIG> arcIGs;
    private int balisePdc;
    private PointDeControlIG pointTempo;
    private ArrayList<String> idEtapeSelect;
    private ArrayList<ArcIG> arcSelect;
    private ArrayList<EtapeIG> entrees;
    private ArrayList<EtapeIG> sorties;
    private CorrespondanceEtapes correspondanceEtapes;
    private GestionnaireClients gestionnaireClients;
    private int nombreClient;


    public MondeIG(){
        arcSelect = new ArrayList<>();
        arcIGs = new ArrayList<>();
        entrees = new ArrayList<>();
        sorties = new ArrayList<>();
        etapeIG = new HashMap<>();
        balisePdc = 0;
        pointTempo = new PointDeControlIG(new ActiviteIG("EtapeTempo","id",1,1));
        idEtapeSelect = new ArrayList<>();
        correspondanceEtapes = new CorrespondanceEtapes();
        nombreClient  = 7 ;
    }

    ////////PARTIE TWISK/////////////

    /**
     * permet de lancer la simulation de l'interface grapgique
     * @throws MondeException
     */
    public void simuler() throws MondeException {
        verifierMondeIG();
        Monde monde = creerMonde();
        ClientTwisk t = new ClientTwisk();
        t.load(monde, this);
    }


    /**
     * Vérifie que le monde est correctement construit et est simulable
     * @throws MondeException
     */
    private void verifierMondeIG() throws MondeException {

        // Dans le meilleur des cas on vérifie que tous les chemins dispoants d'une entrée dispose aussi d'une sortie
        // ici on compte juste qu'il existe au moins une entrée et une sortie

        boolean entree = false;
        boolean sortie = false;


        for(ArcIG arcIG : arcIGs){
            arcIG.getP1().getEtape().ajouterSuccesseurIG(arcIG.getP2().getEtape());
        }

        // On évite deux guichets d'affilée
        for(EtapeIG etapeIG : this){

            if(etapeIG.isUnGuichet()){
                // Un guichet ne peut pas ne pas avoir de successeur
                if(etapeIG.nbSuccesseurIG() < 1)
                    throw new MondeException("Un guichet doit avoir un successeur");


                for ( EtapeIG successeur : etapeIG.gstSuccesseursIG){
                    if(successeur.isUnGuichet()){
                        throw new MondeException("Un guichet ne peut pas suivre un guichet");
                    }else{
                        // On ajoute une atc res après un guichet
                        getEtape(successeur.getIdentifiant()).setRestreinte();
                    }
                }
            }

            if(etapeIG.isUneEntree())
                entree = true;
            if(etapeIG.isUneSortie())
                sortie = true;




        }

        if(!entree || !sortie)
            throw new MondeException("Le monde doit avoir une entrée et une sortie");

    }

    /**
     * Créer un monde à partir du mondeIG
     * @return
     */
    private Monde creerMonde(){

        // Variables
        ArrayList<Etape> entrees = new ArrayList<>();
        ArrayList<Etape> sorties = new ArrayList<>();

        // On reset pour pouvoir lancer à nouveau la simulation
        FabriqueNumero fabrique = FabriqueNumero.getInstance();
        fabrique.reset();

        // Création du monde
        Monde monde = new Monde();

        // Création des étapes
        for(EtapeIG etapeIG : this){

            if(etapeIG.estUneActivite()){
                correspondanceEtapes.ajouter(etapeIG, new Activite(etapeIG.getNom(), etapeIG.getDelai(), etapeIG.getEcart()));
            }else if(etapeIG.isUnGuichet()){
                correspondanceEtapes.ajouter(etapeIG, new Guichet(etapeIG.getNom(), etapeIG.getNbJetons()));
            }else if(etapeIG.estUneActiviteRestreinte()){
                correspondanceEtapes.ajouter(etapeIG, new ActiviteRestreinte(etapeIG.getNom(), etapeIG.getDelai(), etapeIG.getEcart()));
            }
            monde.ajouter(correspondanceEtapes.getEtape(etapeIG));

            // On se souvient des entrées et sorties
            if(etapeIG.isUneEntree())
                entrees.add(correspondanceEtapes.getEtape(etapeIG));

            if(etapeIG.isUneSortie())
                sorties.add(correspondanceEtapes.getEtape(etapeIG));

        }
        // On créer les succeseurs des étapes en fonction des arcs de l'interface graphique.
        for (ArcIG arc : arcIGs) {
            correspondanceEtapes.getEtape(getEtape(arc.getIdentifiantDebut())).ajouterSuccesseur(correspondanceEtapes.getEtape(getEtape(arc.getIdentifiantFin())));
        }

        //entrées/sorties
        monde.aCommeEntree(entrees.toArray(new Etape[entrees.size()]));
        monde.aCommeSortie(sorties.toArray(new Etape[sorties.size()]));

        return monde;
    }

    /////////////////////////////////

    /**
     * Ajoute un act
     * @param type
     */
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

    /**
     * Change les nombre de jetons des guichets
     * @param nbJeton
     * @throws TwiskException
     */
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

    /**
     * met des etapes en entrées
     * @throws TwiskException
     */
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

    /**
     * Met des etapes en sorties
     * @throws TwiskException
     */
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

    /**
     * Ajoute un arc à partir de deux point de controle
     * @param p1
     * @param p2
     */
    public void ajouter(PointDeControlIG p1, PointDeControlIG p2){
        arcIGs.add(new ArcIG(p1, p2));
    }

    public EtapeIG getEtape(String id){
        return etapeIG.get(id);
    }

    /**
     * Modifie les coordonnée d'une act quand elle est déplacée
     * @param etape
     * @param newX
     * @param newY
     */
    public void modifCoordAct(String etape, int newX, int newY){
        EtapeIG etapeTempo = etapeIG.get(etape);
        etapeTempo.setPosX(newX);
        etapeTempo.setPosY(newY);
        etapeTempo.repositionnerPDC();
        etapeIG.remove(etape);
        etapeIG.put(etapeTempo.getIdentifiant(),etapeTempo);
        this.reagir();
    }

    /**
     * modifie les information numérique d'une étape
     * @param delai
     * @param ecart
     * @throws TwiskException
     */
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

    /**
     * remet la selection à zéro
     */
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

    /**
     * réagi à un point de controle cliqué
     * @param point
     * @throws TwiskException
     */
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

    /**
     * Réagi à une étape cliquée
     * @param et
     */
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

    /**
     * Réagi à un arc cliqué
     * @param arcIG
     */
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

    /**
     * Supprime les élément séléctionné
     */
    public void supprSelection(){
        ArrayList<ArcIG> arcASuppr = new ArrayList<>();
        //Enlève tout les arcs
        for (ArcIG arc: arcIGs ) {
            for (EtapeIG etape: this ) {
                if(etape.estSelectionnee()){
                    if(etape.getIdentifiant() == arc.getIdentifiantDebut() || etape.getIdentifiant() == arc.getIdentifiantFin())
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

    public void setGestionnaireClients(GestionnaireClients gestionnaireClients) {
        this.gestionnaireClients = gestionnaireClients;
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

    /**
     * permet de rennomer une act
     * @param newNom
     * @throws TwiskException
     */
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

    public GestionnaireClients getGestionnaireClients() {
        return gestionnaireClients;
    }

    public CorrespondanceEtapes getCorrespondanceEtapes() {
        return correspondanceEtapes;
    }

    /**
     * notifie les observateur
     */
    @Override
    public void reagir(){
        MondeIG mondeIG= this;
        Runnable command = new Runnable() {
            @Override
            public void run() {
                mondeIG.notifierObservateur();
            }
        };
        if(Platform.isFxApplicationThread()){
            command.run();
        }else{
            Platform.runLater(command);
        }
    }


    public int getNombreClient() {
        return nombreClient;
    }

    public void setNombreClient(int nombreClient) {
        this.nombreClient = nombreClient;
    }
}
