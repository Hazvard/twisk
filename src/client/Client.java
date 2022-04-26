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






        // Second monde ----------------------------------------------------------------------

        Monde world1 = new Monde();
        //Les activités
        Etape act6 = new Activite("Début du ZOO", 5, 3);
        Etape guich1 = new Guichet("Achat des tickets ZOO", 3);
        Etape actRes1 = new ActiviteRestreinte("Visite du ZOO", 5, 3);
        Etape act7 = new Activite("fin du ZOO", 5, 3);
        Etape act8 = new Activite("fin du ZOO2", 5, 3);

        //entrée
        world1.aCommeEntree(act6);

        // La suite d'activités
        act6.ajouterSuccesseur(guich1);
        guich1.ajouterSuccesseur(actRes1);
        actRes1.ajouterSuccesseur(act7);
        act7.ajouterSuccesseur(act8);

        //Sortie
        world1.aCommeSortie(act8);

        world1.ajouter(act6, guich1, actRes1, act7, act8);
        // ---------------------------------------------------------------------------------------



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
            simulation.invoke(laSimulation, world1);// Ajout second monde



        }catch(ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e){
            e.printStackTrace();

        }

    }
}
