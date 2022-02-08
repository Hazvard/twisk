package twisk.outils;

public class FabriqueNumero {

    private int cptEtape;

    private FabriqueNumero(){
        cptEtape = 0;
    }

    private static FabriqueNumero instance = new FabriqueNumero();

    public static FabriqueNumero getInstance(){
        return instance;
    }

    public int getNumeroEtpe(){
        return cptEtape;
    }

    void reset(){
        
    }

    

}
