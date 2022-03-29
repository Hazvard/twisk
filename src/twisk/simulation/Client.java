package twisk.simulation;

import twisk.monde.Etape;
import twisk.monde.Monde;

public class Client {

    private int numClient;
    private int rang;
    private Monde monde;


    public Client(int numero){
        numClient = numero;
        rang = 0; // Pas certain de la validité de ça
    }

    public void allerA(Etape etape, int rang){
        this.rang = rang;


    }


}
