package twiskIG.mondeIG;

import java.util.Iterator;




public class ActiviteIG extends EtapeIG {

    private boolean actRestreinte;

    /**
     * Constructeur
     * @param nom
     * @param idf
     * @param larg
     * @param haut
     */
    public ActiviteIG(String nom, String idf, int larg, int haut) {
        super(nom, idf, larg, haut);
        actRestreinte = false;
    }

    /**
     * Rentre un nouveau nombre de jeton
     * @param nouveau
     */
    @Override
    public void changerNbJeton(int nouveau) {
    }

    /**
     * Rend iterable
     * @return
     */
    @Override
    public Iterator<PointDeControlIG> iterator() {
        return null;
    }

    /**
     * renvoie true s'il s'agit d'une activite classqieu
     * @return
     */
    @Override
    public boolean estUneActivite(){
        return !actRestreinte;
    }

    /**
     * renvoie true s'il s'agit d'une activite restreinte
     * @return
     */
    @Override
    public boolean estUneActiviteRestreinte(){
        return  actRestreinte;
    }


    public void setActRestreinte(boolean b){
        actRestreinte = b;
    }

    @Override
    public void setRestreinte() {
        actRestreinte = !actRestreinte;
    }
}
