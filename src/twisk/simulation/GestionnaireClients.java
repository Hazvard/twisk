package twisk.simulation;

import twisk.monde.Etape;

import java.util.ArrayList;
import java.util.Iterator;

public class GestionnaireClients implements Iterable<Client>{

    protected ArrayList<Client> listeClient;

    public GestionnaireClients(){
        listeClient = new ArrayList<>();
    }

    public GestionnaireClients(int nbClient){
        for(int i = 0; i < nbClient; i++){
            listeClient.add(new Client(i));
        }
    }

    public void setClients(int ... tabClient){
        for (int num: tabClient) {
            listeClient.add(new Client(num));
        }
    }

    public void setNbClient(int nbClient){
        //this.nbClient = nbClient;
    }

    public void allerA(int numeroClient, Etape etape, int rang){

    }

    public void nettoyer(){
        listeClient = new ArrayList<>();
    }

    @Override
    public Iterator<Client> iterator() {
        return listeClient.iterator();
    }

    public ArrayList<Client> getListeClient() {
        return listeClient;
    }
}
