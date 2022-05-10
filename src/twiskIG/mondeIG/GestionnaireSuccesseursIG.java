package twiskIG.mondeIG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class GestionnaireSuccesseursIG implements Iterable<EtapeIG>{
    private ArrayList<EtapeIG> listeEtapeIG;

    public GestionnaireSuccesseursIG(){this.listeEtapeIG = new ArrayList<>();}

    public void ajouter(EtapeIG... etapeIGS){listeEtapeIG.addAll(Arrays.asList(etapeIGS));}

    public int nbEtapesIG(){return listeEtapeIG.size();}

    @Override
    public Iterator<EtapeIG> iterator(){return  listeEtapeIG.iterator();}

    public EtapeIG getSuccesseur(){
        if(listeEtapeIG.iterator().hasNext()){
            return listeEtapeIG.iterator().next();
        }
        else return null;
    }

    @Override
    public String toString(){
        return "GestionnaireSuccesseurIG{" +
                "listeEtape=" + listeEtapeIG + '}';
    }
}
