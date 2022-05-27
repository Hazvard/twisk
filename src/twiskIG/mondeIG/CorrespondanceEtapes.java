package twiskIG.mondeIG;


import twisk.monde.Etape;

import java.util.HashMap;

public class CorrespondanceEtapes {

    private HashMap<String, EtapeIG> etapeIG;
    private HashMap<String, Etape> etape;

    public CorrespondanceEtapes(){
        etapeIG = new HashMap<>();
        etape = new HashMap<>();
    }

    public void ajouter(EtapeIG etig, Etape et){
        etapeIG.put(etig.getIdentifiant(), etig);
        etape.put(etig.getIdentifiant(), et);
    }

    public int tailleIG(){
        return etapeIG.size();
    }

    public int taille(){
        return etape.size();
    }

    public Etape get(EtapeIG e){
        return etape.get(e.getIdentifiant());
    }
}
