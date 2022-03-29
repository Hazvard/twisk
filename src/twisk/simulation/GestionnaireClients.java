package twisk.simulation;

import twisk.monde.Etape;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class GestionnaireClients implements Iterable<Client>{

    protected HashMap<Integer, Client> listeClient;

    public GestionnaireClients(){
        listeClient = new HashMap<>();
    }

    public void setClients(int ... tabClient){
        for (int num: tabClient) {
            Client client = new Client(num);
            listeClient.put(client.getNumClient(), client);
        }
    }

    public void allerA(int numeroClient, Etape etape, int rang){
        listeClient.get(numeroClient).allerA(etape,rang);
    }

    public void nettoyer(){
        listeClient = new HashMap<>();
    }

    @Override
    public Iterator<Client> iterator() {
        return listeClient.values().iterator();
    }

    public HashMap<Integer, Client> getListeClient() {
        return listeClient;
    }
}
