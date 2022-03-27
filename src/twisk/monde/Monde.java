package twisk.monde;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Monde implements Iterable<Etape> {

    protected GestionnaireEtape lesEtapes;
    protected SasEntree entree;
    protected SasSortie sortie;
    protected HashMap<Integer, String> constante;
    protected int n;    //Sert à mettre les constantes dans la hashmap

    public Monde() {
        lesEtapes = new GestionnaireEtape();
        entree = new SasEntree();
        sortie = new SasSortie();
        constante = new HashMap<>();
        n = 0;
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
            //constante.put(n, etape.getNom());
            //n++;
        }
        entree.ajouterSuccesseur(etapes);
        ajouter(etapes);
    }

    public void aCommeSortie(Etape... etapes) {
        for (Etape etape : etapes) {
            //constante.put(n, etape.getNom());
            etape.ajouterSuccesseur(sortie);
            etape.setEtapeSortie(true);
            //n++;
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
    public void constantePourC() {
        int n = 0;
        ArrayList<Etape> sortie = new ArrayList<>();
        ArrayList<Etape> entree = new ArrayList<>();
        ArrayList<Etape> etapesNormales = new ArrayList<>();
        if (lesEtapes.nbEtapes() > 0){
            Iterator<Etape> iterator = iterator() ;
            while(iterator.hasNext()) {
                Etape etapeTempo = iterator.next();
                if (etapeTempo.isEtapeEntree()) {
                    //Cas Entrée
                    sortie.add(etapeTempo);
                } else if (!etapeTempo.isEtapeSortie()) {
                    //Cas NON Sortie
                    etapesNormales.add(etapeTempo);
                } else {
                    //Cas Sortie
                    sortie.add(etapeTempo);
                }
            }
            //On ajoute en 1er les entrées
            for (Etape et: entree) {
                constante.put(n, et.getNom());
                n++;
            }//Puis les étapes intermédiaires
            for (Etape et: etapesNormales) {
                constante.put(n, et.getNom());
                n++;
            }//Et enfin les sorties
            for (Etape et: sortie) {
                constante.put(n, et.getNom());
                n++;
            }
        }
    }

    // renvoie #define nom num
    public String definetoC(){
        StringBuilder renvoi = new StringBuilder();
        for(int i = 0; i < constante.size(); i++){
            renvoi.append("#define ");
            renvoi.append(constante.get(i) + " " + i);
            renvoi.append("\n");
        }
        return renvoi.toString();
    }

    public String toC(){
        return ("#include \"def.h\"\n\n \nvoid simulation(int ids){\n"+ entree.toC() + "}");
    }

    public void setNumSortie(int numSortie){
        sortie.setNumEtape(numSortie);
    }

    public int nbGuichets(){
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
