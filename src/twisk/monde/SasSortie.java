package twisk.monde;

public class SasSortie extends Activite {

    /**
     * Constructeur
     */
    public SasSortie() {
        super("Sortie");
    }

    /**
     * Constructeur
     * @param num numéro d'étape
     */
    public SasSortie(int num) {
        super("Sortie", num);
    }

    /**
     * Fonction qui renvoie le code C le l'activitée
     * @return le code C
     */
    public String toC(){
        return "    //Sortie\n";
    }

    @Override
    public String toString() {
        return super.toString() + getNom();
    }

    /**
     * Permet de savoir si l'étape est une sortie ou non
     * @return
     */
    @Override
    public boolean estUneSortie(){return true;}
}
