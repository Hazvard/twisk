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


        try {
            Client leClient = new Client();
            ClassLoaderPerso classLoaderPerso = new ClassLoaderPerso(leClient.getClass().getClassLoader());
            Class<?> laClasse = classLoaderPerso.loadClass("twisk.simulation.Simulation");
            Constructor<?> leConstructeur = laClasse.getConstructor();
            Object laSimulation = leConstructeur.newInstance();
            Method setNbClients = laClasse.getMethod("setNbClient");
            Method simulation = laClasse.getMethod("simuler");
            setNbClients.invoke(laSimulation, 4);
            simulation.invoke(laSimulation, world);


        }catch(ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e){
            e.printStackTrace();

        }
    }
}
