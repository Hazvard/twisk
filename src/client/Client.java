package client;

import twisk.monde.*;
import twisk.outils.ClassLoaderPerso;
import twisk.simulation.Simulation;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Client {

    public Client(){}

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

        //Simulation sim = new Simulation();
        //sim.simuler(world);

        //Monde monde2 = new Monde();
        //Acti
        //Etape act11 = new Activite("db du prc", 5,3);
        //Etape guiche = new Guichet("on est la", 2);
        //Etape actiRest = new ActiviteRestreinte("RestMan", 5, 2);
        //Etape act22 = new Activite("Fin du parc",5,3);
        //Etape act33 = new Activite("Fin du parc 2", 5,3);
        //act11.ajouterSuccesseur(guiche);
        //guiche.ajouterSuccesseur(actiRest);
        //actiRest.ajouterSuccesseur(act22);
        //act22.ajouterSuccesseur(act33);
        //monde2.aCommeSortie(act22);
        //monde2.ajouter(act11,guiche,actiRest,act22);


        try {
            Client leClient = new Client();
            ClassLoaderPerso classLoaderPerso = new ClassLoaderPerso(leClient.getClass().getClassLoader());
            Class<?> laClasse = classLoaderPerso.loadClass("twisk.simulation.Simulation");
            Constructor<?> leConstructeur = laClasse.getConstructor();
            Object laSimulation = leConstructeur.newInstance();
            Method setNbClients = laClasse.getMethod("setNbClients",int.class);
            Method simulation = laClasse.getMethod("simuler", Monde.class);
            setNbClients.invoke(laSimulation, 4);
            simulation.invoke(laSimulation, world);

            //simulation.invoke(laSimulation, monde2);


        }catch(ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e){
            e.printStackTrace();

        }
    }
}
