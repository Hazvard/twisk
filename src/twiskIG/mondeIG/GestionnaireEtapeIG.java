package twiskIG.mondeIG;

import java.util.ArrayList;
import java.util.Iterator;

public class GestionnaireEtapeIG implements Iterable<EtapeIG>{
    protected ArrayList<EtapeIG> listeEtapeIG;

    public GestionnaireEtapeIG(){listeEtapeIG = new ArrayList<>();}


    @Override
    public Iterator<EtapeIG> iterator() {
        return null;
    }
}
