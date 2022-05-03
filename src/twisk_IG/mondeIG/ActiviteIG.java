package twisk_IG.mondeIG;

import java.util.Iterator;

public class ActiviteIG extends EtapeIG {

    private int ecart;
    private int delai;

    public ActiviteIG(String nom, String identifiant, int hauteur, int largeur) {
        super(nom, identifiant, hauteur, largeur);

    }

    public boolean estUneActivite(){
        return true;
    }

}
