package twisk.simulation;

import javafx.concurrent.Task;
import twisk.monde.*;
import twisk.outils.KitC;
import twiskIG.mondeIG.MondeIG;
import twiskIG.mondeIG.SujetObserve;

import java.util.Iterator;

public class Simulation extends SujetObserve implements Iterable<Client> {
    private KitC kitC;
    private GestionnaireClients gestionnaireClients;
    private int nbClient;
    private boolean flag;

    public Simulation(MondeIG mondeIG) {
        kitC = new KitC();
        kitC.creerEnvironnement();
        gestionnaireClients = new GestionnaireClients();
        flag = false;
        mondeIG.setGestionnaireClients(gestionnaireClients);
    }

    public native int[] start_simulation(int nbEtapes, int nbGuichets, int nbClients, int[] tabJetonsGuichet);

    public native int[] ou_sont_les_clients(int nbEtapes, int nbClients);

    public native void nettoyage();

    public void setNbClients(int nb) {
        nbClient = nb;
    }

    private void arreterSimulation(){
        flag = false;
    }

    public void simuler(Monde world) {
        String Cworld = world.toC();

        kitC.creerFichier(Cworld);
        kitC.compiler();

        kitC.construireLaLibrairie(world.getNumMonde());

        System.load("/tmp/twisk/libTwisk"+ world.getNumMonde() +".so"); // Ajout séance 6

        int nbEtape = world.nbEtapes();
        int nbGuichet = world.nbGuichets();
        int[] tabJetonGuichet = new int[nbGuichet];

        int i = 0;
        for (Etape etape : world) {
            if (etape.estUnGuichet()) {
                Guichet guichetTemp = (Guichet) etape;
                tabJetonGuichet[i] = guichetTemp.getNbjetons();
                i++;
            }
        }

        flag = true;



        int[] tabSimu = start_simulation(nbEtape, nbGuichet, nbClient, tabJetonGuichet);
        gestionnaireClients.setClients(tabSimu);
        //System.out.println("Les Clients : " + tabSimu[0]  + " " +  tabSimu[1]  +" " + tabSimu[2]  +  " " + tabSimu[3]  + " ");

        System.out.print("Les clients : ");
        for (Client client: gestionnaireClients) {
            System.out.print(client.getNumClient() + "    ");
        }
        System.out.println("");

        while (flag) {

            i = 0;
            int[] tabClient = ou_sont_les_clients(nbEtape, nbClient);
            for (Etape etape : world) {
                if (etape.isEtapeEntree()) {
                    System.out.println("Entrée : " + etape.getNom() + " ===>  " + tabClient[i * (nbClient + 1)]);
                } else if (etape.isEtapeSortie()) {
                    System.out.println("Sortie : " + etape.getNom() + " ===>  " + tabClient[i * (nbClient + 1)]);
                } else if (etape.estUnGuichet()) {
                    System.out.println("Guichet : " + etape.getNom() + " ===>  " + tabClient[i * (nbClient + 1)]);
                } else if (etape.estUneActivite()) {
                    System.out.println("Activité : " + etape.getNom() + " ===>  " + tabClient[i * (nbClient + 1)]);
                }
                System.out.print("Clients (" + tabClient[i * (nbClient + 1)] + ") :");
                //for (Client clients: gestionnaireClients
                //     ) {
                //    System.out.print(clients.getNumClient() + "    ");
                //}
                for(int j = 0; j < tabClient[i*(nbClient + 1)]; j++) {
                    System.out.print("   " + tabClient[j + (nbClient * i) + i + 1]);
                    gestionnaireClients.allerA(tabClient[j + (nbClient * i) + i + 1],etape, i + 1);
                }
                System.out.println("");

                i++;
                System.out.println("\n");
            }
            System.out.println("");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (nbClient == tabClient[(nbClient * nbEtape + nbEtape -nbClient - 1)])
                flag = false;



            System.out.println();
        }
        nettoyage();
        gestionnaireClients.nettoyer();
    }


    @Override
    public void reagir() {

    }

    @Override
    public Iterator<Client> iterator() {
        return null;
    }
}