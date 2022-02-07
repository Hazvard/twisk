package Twisk.monde;

import java.util.Iterator;

public abstract class Etape implements Iterable {

    private String nom;
    protected GestionnaireSuccesseurs Gstsuccesseurs;

    public Etape(String nom) {
        this.nom = nom;
    }

    public void ajouterSuccesseur(Etape ... e) {
        getGstsuccesseurs().ajouter(e);
    }

    public boolean estUneActivite(){
        return false;
    }

    public boolean estUnGuichet(){
        return false;
    }

    public Iterator<Etape> iterator(){
        return this.iterator();
    }


    public String toString() {
        return nom ; // A voir lorsque l'on aura avancer dans les classes inférieure
    }


    // Getteurs

    public GestionnaireSuccesseurs getGstsuccesseurs() {
        return Gstsuccesseurs;
    }

    public String getNom() {
        return nom;
    }

    // Setteurs

    public void setGstsuccesseurs(GestionnaireSuccesseurs gstsuccesseurs) {
        Gstsuccesseurs = gstsuccesseurs;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    // Fonctions de controles


}