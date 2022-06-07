package twisk.monde;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class GestionnaireSuccesseurs implements Iterable<Etape>{

    private ArrayList<Etape> listeEtape ;

    /**
     * Constructeur
     */
    public GestionnaireSuccesseurs() {
        this.listeEtape = new ArrayList<>();
    }

    /**
     * Ajoute un liste de successeurs
     * @param etapes
     */
    public void ajouter(Etape... etapes){
        listeEtape.addAll(Arrays.asList(etapes)) ;
    }

    /**
     * renvoie la nombre de successeurs
     * @return taille de listeEtape
     */
    public int nbEtapes(){
        return listeEtape.size();
    }

    /**
     * renvoie la nombre de successeurs
     * @return taille de listeEtape
     */
    public int nbSuccesseurs(){
        return listeEtape.size();
    }
    /**
     * rend la class iterable
     * @return
     */
    public Iterator<Etape> iterator(){
        return listeEtape.iterator();
    }

    // getteurs

    public ArrayList<Etape> getListeEtape() {
        return listeEtape;
    }

    public Etape getSuccesseur(){
        if(listeEtape.iterator().hasNext())
            return listeEtape.iterator().next();
        else
            return null;
    }





    // Setteurs
    public void setListeEtape(ArrayList<Etape> listeEtape) {
        this.listeEtape = listeEtape;
    }

    @Override
    public String toString() {
        return "GestionnaireSuccesseurs{" +
                "listeEtape=" + listeEtape.toString() +
                '}';
    }
}
