package monde;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class GestionnaireSuccesseurs {
    private ArrayList<Etape> listeEtape ;


    public GestionnaireSuccesseurs() {
        this.listeEtape = new ArrayList<>();
    }


    public void ajouter(Etape... etapes){
        listeEtape.addAll(Arrays.asList(etapes)) ;
    }

    public int nbEtapes(){
        return listeEtape.size();
    }

    public Iterator<Etape> iterator(){
        return  null ;
    }

    // getteurs

    public ArrayList<Etape> getListeEtape() {
        return listeEtape;
    }


    // Setteurs
    public void setListeEtape(ArrayList<Etape> listeEtape) {
        this.listeEtape = listeEtape;
    }
}
