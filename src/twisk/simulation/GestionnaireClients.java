package twisk.simulation;

import twisk.monde.Etape;

import java.util.HashMap;
import java.util.Iterator;

public class GestionnaireClients implements Iterable<Client>{

    protected HashMap<Integer, Client> listeClient;

    /**
     * Constructeur
     */
    public GestionnaireClients(){
        listeClient = new HashMap<>();
    }

    public void setClients(int ... tabClient){
        for (int num: tabClient) {
            Client client = new Client(num);
            listeClient.put(client.getNumClient(), client);
        }
    }

    public int getNbClient(){
        return listeClient.size();
    }

    /**
     * Déplace un client
     * @param numeroClient
     * @param etape
     * @param rang
     */
    public void allerA(int numeroClient, Etape etape, int rang){
        listeClient.get(numeroClient).allerA(etape,rang);
    }

    /**
     * Supprime la hasmap des client
     */
    public void nettoyer(){
        listeClient = new HashMap<>();
    }

    /**
     * rend la class iterable
     * @return
     */
    @Override
    public Iterator<Client> iterator() {
        return listeClient.values().iterator();
    }

    public HashMap<Integer, Client> getListeClient() {
        return listeClient;
    }

    @Override
    public String toString() {
        return "GestionnaireClients{" +
                "listeClient=" + listeClient +
                '}';
    }
}
