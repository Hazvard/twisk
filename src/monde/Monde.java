package monde;

import java.util.Iterator;

public class Monde implements Iterable<Etape>{

    protected GestionnaireEtape lesEtapes;
    protected SasEntree entree ;
    protected SasSortie sortie ;

    public Monde(){
        lesEtapes = new GestionnaireEtape();

    }
//https://learntutorials.net/fr/java/topic/172/iterateur-et-iterable
    @Override
    public String toString() {
        return "Monde{" +
                "gstEtape=" + lesEtapes +
                ", entree=" + entree +
                ", sortie=" + sortie +
                '}';
    }

    void aCommeEntree(Etape ... etapes){
        //appel de estUneEntre
    }

    void aCommeSortie(Etape ... etapes){
        //appel de estUneSortie
    }

    int nbEtapes() {
        int n = 0;
        for (Etape e:lesEtapes
             ) {
            if (!e.estUnGuichet()) {
                n++;
            }
        }
        return n;
    }


    int nbGuichets(){
        int n = 0;
        for(Etape e: lesEtapes){
            if(e.estUnGuichet()){
                n++;
            }
        }
        return n;
    }


    @Override
    public Iterator<Etape> iterator() {
        return lesEtapes.iterator();
    }
}
