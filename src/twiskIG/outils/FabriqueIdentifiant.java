package twiskIG.outils;

public class FabriqueIdentifiant {
    private int noEtape;
    private int noPointDeControl;
    private static FabriqueIdentifiant instance = new FabriqueIdentifiant();
    private int noGuichet;

    /**
     * Renvoie l'instance de la fabrique
     * @return
     */
    static public FabriqueIdentifiant getInstance(){
        return instance;
    }

    public String getIdentiantEtape(){
        noEtape++;
        return "n°" + (noEtape - 1);
    }

    public String getIdentifiantPointDeControl(){
        noPointDeControl ++;
        return "n°" + (noPointDeControl - 1);
    }

    public String getIdentifiantGuichet(){
        noGuichet++;
        return  "n°" + (noGuichet - 1);
    }
}
