package client;

import twisk.monde.*;
import twisk.simulation.Simulation;

public class Client {


    public static void main(String[] args) {
        Monde world = new Monde();
        //Les activités
        Etape act1 = new Activite("Début du parc", 5, 3);
        Etape guich = new Guichet("Achat des tickets", 3);
        Etape actRes = new ActiviteRestreinte("Visite du parc", 5, 3);
        Etape act2 = new Activite("fin du parc", 5, 3);
        Etape act3 = new Activite("fin du parc2", 5, 3);




        //entrée
        world.aCommeEntree(act1);

        // La suite d'activités
        act1.ajouterSuccesseur(guich);
        guich.ajouterSuccesseur(actRes);
        actRes.ajouterSuccesseur(act2);
        act2.ajouterSuccesseur(act3);

        //Sortie
        world.aCommeSortie(act3);

        world.ajouter(act1, guich, actRes, act2, act3);

        Simulation sim = new Simulation();
        sim.simuler(world);
    }
}
