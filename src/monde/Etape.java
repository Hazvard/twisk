package monde;

import java.util.Iterator;

public abstract class Etape implements Iterable {
    private String nom;
    protected GestionnaireSuccesseurs Gstsuccesseurs;

    public Etape(String nom) {
        this.nom = nom;
    }

    public void ajouterSuccesseur(Etape... e) {
    }

    public boolean estUneActivite(){
        return false;
    }

    public boolean estUnGuichet(){
        return false;
    }

    public Iterator<Etape> iterator(){
        return null;
    }
}