package monde;

import java.util.ArrayList;
import java.util.Iterator;

public class GestionnaireEtapte implements Iterable{

    protected ArrayList<Etape> listeEtape ;

    public GestionnaireEtapte(){
        //Constru vide pour le moment
    }

    public void ajouter(Etape ... e){
        for (Etape etape:e) {
            listeEtape.add(etape);
        }
    }

    int nbEtapes(){
        return listeEtape.size();
    }


    @Override
    public Iterator<Etape> iterator() {
        return null;
    }
}