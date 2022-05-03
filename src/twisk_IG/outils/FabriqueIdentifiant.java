package twisk_IG.outils;

public class FabriqueIdentifiant {

    private int noEtape ;


    private static FabriqueIdentifiant instance = new FabriqueIdentifiant() ;

    static public FabriqueIdentifiant getInstance(){
        return instance;
    }



    public String getIdentifiant(){
        noEtape++;
        return String.valueOf(noEtape) ;
    }

    public void reset(){
        noEtape = 0;
    }

}
