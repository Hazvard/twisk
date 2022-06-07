package twisk.monde;

import twisk.outils.FabriqueNumero;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Monde implements Iterable<Etape> {

    protected GestionnaireEtape lesEtapes;
    protected SasEntree entree;
    protected SasSortie sortie;
    protected HashMap<Integer, String> constante;
    protected int n;    //Sert à mettre les constantes dans la hashmap
    protected int numMonde;

    /**
     * Constructeur de monde
     */
    public Monde() {
        lesEtapes = new GestionnaireEtape();
        entree = new SasEntree();
        //sortie = new SasSortie();
        ajouter(entree);
        constante = new HashMap<>();
        n = 0;
        this.numMonde = FabriqueNumero.getInstance().getNumeroMonde();
    }

    @Override
    public String toString() {
        return "Monde : \n" +
                "gstEtape=" + lesEtapes.toString() + " ;\n" +
                "entree = " + entree.toString() + " ;\n" +
                "sortie = " + sortie.toString() + " ;\n";
    }

    /**
     * Ajoute une liste d'étapes au monde
     * @param etapes liste d'étapes à ajouter au monde
     */
    public void ajouter(Etape... etapes) {
        lesEtapes.ajouter(etapes);
    }

    /**
     * Défine des étapes comme entrées du monde, et les ajoutes au monde
     * @param etapes liste d'étapes entrées
     */
    public void aCommeEntree(Etape... etapes) {
        for(Etape etape: lesEtapes){
            for(Etape entre: etapes){
                if(etape.getNumEtape() == entre.getNumEtape())
                    etape.setEtapeEntree(true);
                    this.entree.ajouterSuccesseur(entre);
            }
        }
    }

    /**
     * Définie des étapes comme sorties du monde et les ajoute à celui-ci
     * @param etapes liste d'étapes sorties
     */
    public void aCommeSortie(Etape... etapes) {
        sortie = new SasSortie();

        for(Etape sorties: etapes){
            for(Etape etape: lesEtapes){
                if(etape.getNumEtape() == sorties.getNumEtape())
                    etape.setEtapeSortie(true);
            }
            sorties.ajouterSuccesseur(this.sortie);
        }
        ajouter(sortie);
    }

    /**
     * Renvoie le nombre d'étapes que contient le monde
     * @return la taille de lesEtapes
     */
    public int nbEtapes() {
        return lesEtapes.nbEtapes();
    }



    // renvoie #define nom num

    /**
     * Créer les defines du monde pour la fonction toC
     * @return renvoie le code C des difine pour le monde
     */
    public String definetoC(){
        StringBuilder renvoi = new StringBuilder();
        for(int i = 0; i < constante.size(); i++){
            renvoi.append("#define ");
            renvoi.append(constante.get(i) + " " + i);
            renvoi.append("\n");
        }
        return renvoi.toString();
    }

    /**
     * Fonction qui renvoie le code C le l'activitée
     * @return le code C
     */
    public String toC(){
        StringBuilder entete = new StringBuilder("#include \"def.h\"\n");
        for(Etape etape: lesEtapes){
            entete.append("//#define " + etape.getNom() + " " + etape.getNumEtape() + "\n");
        }
        entete.append("\n\n\nvoid simulation(int ids){\n\n  srand(time(NULL));\n");
        return ( entete + entree.toC() + "}");
    }



    /**
     * Fonction qui compte le nombre de guichet et le retourne
     * @return nombre de guichet
     */
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

    public int getNumMonde(){
        return numMonde;
    }

    public void setNumSortie(int numSortie){
        sortie.setNumEtape(numSortie);
    }

    /**
     * Rend iterable la classe
     * @return
     */
    @Override
    public Iterator<Etape> iterator() {
        return lesEtapes.iterator();
    }
}

/* Fonction non utilisée

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

 */