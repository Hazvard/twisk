package twisk.monde;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class GestionnaireEtape implements Iterable<Etape>{

    protected ArrayList<Etape> listeEtape ;

    /**
     * Constructeur
     */
    public GestionnaireEtape(){
        listeEtape = new ArrayList<Etape>();
    }

    /**
     * Ajouter une liste d'étapes
     * @param etapes liste d'étapes
     */
    public void ajouter(Etape ... etapes) {
        listeEtape.addAll(Arrays.asList(etapes));
    }

    /**
     * renvoie me nombre d'étapes dans la liste
     * @return
     */
    public int nbEtapes(){
        return listeEtape.size();
    }


    /**
     * Rend la class iterable
     * @return
     */
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