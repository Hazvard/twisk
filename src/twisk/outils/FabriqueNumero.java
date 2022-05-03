package twisk.outils;

import java.util.concurrent.Semaphore;

public class FabriqueNumero {

    private int cptEtape;
    private int cptMonde;
    private int cptSemaphore;

    private static FabriqueNumero instance = new FabriqueNumero();

    public static FabriqueNumero getInstance(){
        return instance;
    }

    public int getNumeroEtpe(){
        return cptEtape ++;
    }
    public int getNumeroMonde(){
        return cptMonde ++;
    }

    public int getCptSemaphore() {
        cptSemaphore++;
        return cptSemaphore ;
    }

    public  void reset(){
      cptEtape = 0;
      cptMonde = 0;
      cptSemaphore = 1;
    }
}
