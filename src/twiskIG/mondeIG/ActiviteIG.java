package twiskIG.mondeIG;

import java.util.Iterator;




public class ActiviteIG extends EtapeIG {

    private boolean actRestreinte;


    public ActiviteIG(String nom, String idf, int larg, int haut) {
        super(nom, idf, larg, haut);
        actRestreinte = false;
    }

    @Override
    public void changerNbJeton(int nouveau) {
    }

    @Override
    public Iterator<PointDeControlIG> iterator() {
        return null;
    }

    @Override
    public boolean estUneActivite(){
        return !actRestreinte;
    }

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
