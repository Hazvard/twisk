package twisk.monde;

import twisk.outils.FabriqueNumero;

import java.util.Iterator;

public abstract class Etape implements Iterable {

    private String nom;
    protected GestionnaireSuccesseurs gstsuccesseurs;
    private int numEtape ;
    private boolean etapeEntree;
    private boolean etapeSortie;

    public Etape(String nom, int num) {
        this.nom = nom;
        this.gstsuccesseurs = new GestionnaireSuccesseurs();
        this.numEtape = num ;
        this.etapeEntree = false;
        this.etapeSortie = false;
    }

    public boolean isEtapeEntree() {
        return etapeEntree;
    }

    public boolean isEtapeSortie() {
        return etapeSortie;
    }

    public void setEtapeEntree(boolean etapeEntree) {
        this.etapeEntree = etapeEntree;
    }

    public void setEtapeSortie(boolean etapeSortie) {
        this.etapeSortie = etapeSortie;
    }

    public Etape(String nom) {
        this.nom = nom;
        this.gstsuccesseurs = new GestionnaireSuccesseurs();
        FabriqueNumero fabrique = FabriqueNumero.getInstance();
        this.numEtape = fabrique.getNumeroEtpe();
    }

    public void ajouterSuccesseur(Etape ... e) {
        gstsuccesseurs.ajouter(e);
    }

    public int nbSuccesseurs(){
        return gstsuccesseurs.nbEtapes();
    }

    public boolean estUneActivite(){
        return false;
    }

    public boolean estUnGuichet(){
        return false;
    }

    public Iterator<Etape> iterator(){
        return gstsuccesseurs.iterator();
    }

    public String toC(){return "";}


    public String toString() {
        return nom ; // A voir lorsque l'on aura avancer dans les classes inférieure
    }


    // Getteurs

    public GestionnaireSuccesseurs getGstsuccesseurs() {
        return gstsuccesseurs;
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