package twiskIG.mondeIG;

import java.util.Iterator;

public class GuichetIG extends EtapeIG {
    private String nom;
    private String identifiant;
    private int posX;
    private int posY;
    private int largeur;
    private int hauteur;
    private int delai;
    private int ecart;
    private PointDeControlIG[] pdc;
    private boolean estSelect;
    private boolean estUneEntre;
    private boolean estUneSortie;

    private int nbJetons;

    public GuichetIG(String nom, String idf, int larg, int haut, int jetons) {
        super(nom, idf, larg, haut);
        nbJetons = jetons;
    }

    @Override
    public void changerNbJeton(int nouveau){
        nbJetons = nouveau;
    }

    @Override
    public int getNbJetons(){return nbJetons;}

    @Override
    public boolean isUnGuichet(){return true;}

    @Override
    public Iterator<PointDeControlIG> iterator() {
        return null;
    }

    @Override
    public boolean estUnguichet(){
        return true;
    }
}
