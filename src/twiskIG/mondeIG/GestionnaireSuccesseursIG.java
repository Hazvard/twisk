package twiskIG.mondeIG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class GestionnaireSuccesseursIG implements Iterable<EtapeIG>{
    private ArrayList<EtapeIG> listeEtapeIG;

    /**
     * Constructeur
     */
    public GestionnaireSuccesseursIG(){this.listeEtapeIG = new ArrayList<>();}

    /**
     * Ajoute une liste d'étapesIG
     * @param etapeIGS
     */
    public void ajouter(EtapeIG... etapeIGS){listeEtapeIG.addAll(Arrays.asList(etapeIGS));}

    public int nbEtapesIG(){return listeEtapeIG.size();}

    @Override
    public Iterator<EtapeIG> iterator(){return  listeEtapeIG.iterator();}

    @Override
    public String toString(){
        return "GestionnaireSuccesseurIG{" +
                "listeEtape=" + listeEtapeIG + '}';
    }
}
