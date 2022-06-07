package twisk.outils;

import java.util.concurrent.Semaphore;

public class FabriqueNumero {

    private int cptEtape;
    private int cptMonde;
    private int cptSemaphore;

    /**
     * permet de renvoyer une instance de fabrique numéros
     */
    private static FabriqueNumero instance = new FabriqueNumero();

    /**
     * renvoyer une instance de la fabrique
     * @return
     */
    public static FabriqueNumero getInstance(){
        return instance;
    }


    /**
     * Renvoie un numéro d'étape et l'incrémente
     * @return numEtape
     */
    public int getNumeroEtpe(){
        return cptEtape ++;
    }

    /**
     * Renvoie un numéro de monde et l'incrémente
     * @return cptMonde
     */
    public int getNumeroMonde(){
        return cptMonde ++;
    }

    /**
     * Renvoie un numéro de sémaphore et l'incrémente
     * @return cptSemaphore
     */
    public int getCptSemaphore() {
        cptSemaphore++;
        return cptSemaphore ;
    }

    /**
     * permet de reset les compteurs
     */
    public  void reset(){
        // On de reset pas le compteur de monde, car il faut jamais repartir au début à cause de la lib
      cptEtape = 0;
      cptSemaphore = 1;
    }
}
