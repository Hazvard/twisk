package twisk.simulation;

import twisk.monde.Etape;
import twisk.monde.Monde;

public class Client {

    private int numClient;
    private int rang;
    private Etape etape;


    public Client(int numero){
        numClient = numero;
        rang = 0;
        etape = null;
    }

    public void allerA(Etape etape, int rang){
        this.rang = rang;
        this.etape = etape;
    }



    // Getteurs
    public int getNumClient() {
        return numClient;
    }

    public void setNumClient(int numClient) {
        this.numClient = numClient;
    }

    public int getRang() {
        return rang;
    }


    // Setteurs
    public void setRang(int rang) {
        this.rang = rang;
    }

    public Etape getEtape() {
        return etape;
    }

    public void setEtape(Etape etape) {
        this.etape = etape;
    }

    @Override
    public String toString() {
        return "Client{" +
                "numClient=" + numClient +
                ", rang=" + rang +
                ", etape=" + etape +
                '}';
    }
}
