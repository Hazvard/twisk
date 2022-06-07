package twiskIG.mondeIG;


import twisk.monde.Etape;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CorrespondanceEtapes {

    private HashMap<String, EtapeIG> etapeIG;
    private HashMap<String, Etape> etape;
    private HashMap<EtapeIG, Etape> bonneHashMap;

    /**
     * Constructeur
     */
    public CorrespondanceEtapes(){
        etapeIG = new HashMap<>();
        etape = new HashMap<>();
        bonneHashMap = new HashMap<>();
    }

    /**
     * Ajoute une étape de chaque dans les map
     * @param etig
     * @param et
     */
    public void ajouter(EtapeIG etig, Etape et){
        etapeIG.put(etig.getIdentifiant(), etig);
        etape.put(etig.getIdentifiant(), et);
        bonneHashMap.put(etig, et);
    }

    public int tailleIG(){
        return etapeIG.size();
    }

    public int taille(){
        return etape.size();
    }

    public Etape getEtape(EtapeIG e){
        return bonneHashMap.get(e);
    }
    public EtapeIG getEtapeIG(Etape e){
        for (Map.Entry<EtapeIG,Etape> entry:bonneHashMap.entrySet()){
            if(Objects.equals(e, entry.getValue())){
                return entry.getKey();
            }
        }
        return null;
    }
}
