package monde;

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

    int nbEtapes(){
        return listeEtape.size();
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