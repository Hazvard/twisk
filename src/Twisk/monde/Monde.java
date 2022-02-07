package Twisk.monde;

import java.util.Iterator;

public class Monde implements Iterable<Etape>{

    protected GestionnaireEtape lesEtapes;
    protected SasEntree entree ;
    protected SasSortie sortie ;

    public Monde(){
        lesEtapes = new GestionnaireEtape();

    }

    @Override
    public String toString() {
        return "Monde{" +
                "gstEtape=" + lesEtapes +
                ", entree=" + entree +
                ", sortie=" + sortie +
                '}';
    }

    public void ajouter(Etape ... etapes){
        lesEtapes.ajouter(etapes);
    }

    public void aCommeEntree(Etape ... etapes){
        entree.ajouterSuccesseur(etapes);
    }

    public void aCommeSortie(Etape ... etapes){
        for( Etape etape : etapes){
            etape.ajouterSuccesseur(sortie);
        }
    }

    public int nbEtapes() {
        int n = 0;
        Iterator<Etape> iterGstEtapes =  lesEtapes.iterator() ;
        for (Iterator<Etape> it = iterGstEtapes; it.hasNext(); ) {
            Etape e = it.next();
            if (!e.estUnGuichet()) {
                n++;
            }
        }
        return n;
    }


    public int nbGuichets(){
        int n = 0;
        Iterator<Etape> iterGstEtapes =  lesEtapes.iterator() ;
        for (Iterator<Etape> it = iterGstEtapes; it.hasNext(); ) {
            Etape e = it.next();
            if (e.estUnGuichet()) {
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
