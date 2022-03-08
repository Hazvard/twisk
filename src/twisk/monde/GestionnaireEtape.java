package twisk.monde;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class GestionnaireEtape implements Iterable{

    protected ArrayList<Etape> listeEtape ;

    public GestionnaireEtape(){
        listeEtape = new ArrayList<Etape>();
    }

    public void ajouter(Etape ... etapes) {
        listeEtape.addAll(Arrays.asList(etapes));
    }

    public int nbEtapes(){
        return listeEtape.size();
    }


    public String toC(){
        StringBuilder retour = new StringBuilder(listeEtape.size());
        for (Etape etape: listeEtape) {
            retour.append(etape.toC());
        }
        return retour.toString();
    }

    @Override
    public Iterator<Etape> iterator() {
        return listeEtape.iterator();
    }

    @Override
    public String toString() {
        return "GestionnaireEtape{ " +
                "listeEtape = " + listeEtape +
                '}';
    }
}