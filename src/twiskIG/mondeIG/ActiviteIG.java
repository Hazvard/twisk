package twiskIG.mondeIG;

import java.util.Iterator;

public class ActiviteIG extends EtapeIG {
    public ActiviteIG(String nom, String idf, int larg, int haut) {
        super(nom, idf, larg, haut);
    }

    @Override
    public void changerNbJeton(int nouveau) {
    }

    @Override
    public Iterator<PointDeControlIG> iterator() {
        return null;
    }
}
