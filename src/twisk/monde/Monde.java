package twisk.monde;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Monde implements Iterable<Etape> {

    protected GestionnaireEtape lesEtapes;
    protected SasEntree entree;
    protected SasSortie sortie;
    protected HashMap<Integer, String> constante;

    public Monde() {
        lesEtapes = new GestionnaireEtape();
        entree = new SasEntree();
        sortie = new SasSortie();
        constante = new HashMap<>();
    }

    @Override
    public String toString() {
        return "Monde : \n" +
                "gstEtape=" + lesEtapes.toString() + " ;\n" +
                "entree = " + entree.toString() + " ;\n" +
                "sortie = " + sortie.toString() + " ;\n";
    }

    public void ajouter(Etape... etapes) {
        lesEtapes.ajouter(etapes);
    }

    public void aCommeEntree(Etape... etapes) {
        for (Etape etape : etapes) {
            etape.setEtapeEntree(true);
        }
        entree.ajouterSuccesseur(etapes);
        ajouter(etapes);
    }

    public void aCommeSortie(Etape... etapes) {
        for (Etape etape : etapes) {
            etape.ajouterSuccesseur(sortie);
            etape.setEtapeSortie(true);
        }
        ajouter(etapes);
    }

    public int nbEtapes() {
        return lesEtapes.nbEtapes();
    }


    //POUR L'INSTANT :
        //Renvoie en String le contenu de l'hashmap constante
    //Objectif :
        //Renvoie la hashmap et à adapter dans le toC pour remplacer
        //les numeros des étapes par leur nom...
    public String constantePourC() {
        int n = 0;
        ArrayList<Etape> sortie = new ArrayList<>();
        if (lesEtapes.nbEtapes() > 0){
            Iterator<Etape> iterator = iterator() ;
            while(iterator.hasNext()) {
                Etape etapeTempo = iterator.next();
                if(etapeTempo.isEtapeEntree()){
                    //Cas Entrée
                    constante.put(n, etapeTempo.getNom());
                    n++;
                }
                else if(!etapeTempo.isEtapeSortie()){
                    //Cas NON Sortie
                    constante.put(n, etapeTempo.getNom());
                    n++;
                }
                else{
                    //Cas Sortie
                    sortie.add(etapeTempo);
                }
            }
            for (Etape et: sortie) {
                constante.put(n, et.getNom());
                n++;
            }
        }
        return constante.toString();
    }

    public String toC(){
        return "#include \"def.h\"\n\nvoid simulation(int ids){\n"+ entree.toC() + "}";
    }

    public void setNumSortie(int numSortie){
        sortie.setNumEtape(numSortie);
    }

    public int nbGuichets(){//Marche pas ________
        int n = 0;
        if (lesEtapes.nbEtapes() > 0){
            Iterator<Etape> iterator = iterator() ;
            while(iterator.hasNext()) {
                Etape etape = iterator.next();
                if(etape.estUnGuichet())
                    n++  ;
            }
        }
        return n;
    }


    @Override
    public Iterator<Etape> iterator() {
        return lesEtapes.iterator();
    }
}
