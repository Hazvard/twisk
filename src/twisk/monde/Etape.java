package twisk.monde;

import twisk.outils.FabriqueNumero;

import java.util.Iterator;

public abstract class Etape implements Iterable {

    private String nom;
    protected GestionnaireSuccesseurs gstsuccesseurs;
    private int numEtape ;
    private boolean etapeEntree;
    private boolean etapeSortie;


    /**
     * Constructeur
     * @param nom nom de étape
     * @param num numéros d'une étape
     */
    public Etape(String nom, int num) {
        this.nom = nom;
        this.gstsuccesseurs = new GestionnaireSuccesseurs();
        this.numEtape = num ;
        this.etapeEntree = false;
        this.etapeSortie = false;
    }

    /**
     * Constructeur
     * @param nom nom de étape
     */
    public Etape(String nom) {
        this.nom = nom;
        this.gstsuccesseurs = new GestionnaireSuccesseurs();
        FabriqueNumero fabrique = FabriqueNumero.getInstance();
        this.numEtape = fabrique.getNumeroEtpe();
    }
    /**
     * Renvoie le booléen qui indique s'il sagit d'une activité ou non
     * @return
     */
    public boolean isEtapeEntree() {
        return etapeEntree;
    }

    /**
     * Renvoie le booléen qui indique s'il sagit d'une sortie ou non
     * @return
     */
    public boolean isEtapeSortie() {
        return etapeSortie;
    }


    public void setEtapeEntree(boolean etapeEntree) {
        this.etapeEntree = etapeEntree;
    }

    public void setEtapeSortie(boolean etapeSortie) {
        this.etapeSortie = etapeSortie;
    }


    /**
     * Permet d'ajouter une liste de successeurs à une étape
     * @param e liste d'étape successeurs
     */
    public void ajouterSuccesseur(Etape ... e) {
        gstsuccesseurs.ajouter(e);
    }

    /**
     * Renvoie le nombre de successeurs
     * @return nombre de successeurs
     */
    public int nbSuccesseurs(){
        return gstsuccesseurs.nbEtapes();
    }

    /**
     * Permet de savoir s'il s'agit d'une activité
     * @return true si oui false sinon
     */
    public boolean estUneActivite(){
        return false;
    }

    /**
     * Permet de savoir s'il s'agit d'une activité Restreinte
     * @return true si oui false sinon
     */
    public boolean estUneActiviteRes(){
        return false;
    }

    /**
     * Permet de savoir s'il s'agit d'un guichet
     * @return true si oui false sinon
     */
    public boolean estUnGuichet(){
        return false;
    }

    /**
     * renvoi un Iterator afin de récupérer les successeurs
     * @return iterateur des successeurs
     */
    public Iterator<Etape> iterator(){
        return gstsuccesseurs.iterator();
    }

    /**
     * Fonction qui renvoie le code C le l'activitée
     * @return le code C
     */
    public String toC(){return "";}


    public String toString() {
        return nom ; // A voir lorsque l'on aura avancer dans les classes inférieure
    }


    // Getteurs

    public GestionnaireSuccesseurs getGstsuccesseurs() {
        return gstsuccesseurs;
    }

    public int nombreDeSuccesseurs(){
        return gstsuccesseurs.nbSuccesseurs();
    }

    public String getNom() {
        return nom;
    }

    public int getNumEtape() {
        return numEtape;
    }

    public int getTemps(){return 0;}
    public int getEcartTemps() {return 0;}

// Setteurs

    public void setGstsuccesseurs(GestionnaireSuccesseurs gstsuccesseurs) {
        gstsuccesseurs = gstsuccesseurs;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNumEtape(int numEtape){
        this.numEtape = numEtape;
    }

    // Fonctions de controles


}