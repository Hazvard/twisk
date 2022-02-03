package monde;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class GestionnaireEtape implements Iterable{

    protected ArrayList<Etape> listeEtape ;

    public GestionnaireEtape(){
        listeEtape = new ArrayList<>();
    }

    public void ajouter(Etape ... etapes) {
        listeEtape.addAll(Arrays.asList(etapes));
    }

    int nbEtapes(){
        return listeEtape.size();
    }


    @Override
    public Iterator<Etape> iterator() {
        return null;
    }
}