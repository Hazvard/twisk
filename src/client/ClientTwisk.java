package client;

import twisk.monde.*;
import twisk.outils.ClassLoaderPerso;
import twiskIG.mondeIG.MondeIG;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClientTwisk {

    public ClientTwisk(){}

    public void test(Monde monde, MondeIG mondeIG){

        try {
            ClassLoaderPerso classLoaderPerso = new ClassLoaderPerso(this.getClass().getClassLoader());
            Class<?> laClasse = classLoaderPerso.loadClass("twisk.simulation.Simulation");
            Constructor<?> leConstructeur = laClasse.getConstructor(MondeIG.class);
            Object laSimulation = leConstructeur.newInstance(mondeIG);
            Method setNbClients = laClasse.getMethod("setNbClients",int.class);
            Method simulation = laClasse.getMethod("simuler", Monde.class);
            setNbClients.invoke(laSimulation, mondeIG.getNombreClient());
            simulation.invoke(laSimulation, monde);
            classLoaderPerso.finalize();



        }catch(ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e){
            e.printStackTrace();

        }
    }

    /*public static void main(String[] args) {

        Monde world = new Monde();
        //Les activités
        Etape act1 = new Activite("Début du parc", 2, 1);
        Etape guich = new Guichet("Achat des tickets", 3);
        Etape actRes = new ActiviteRestreinte("Visite du parc", 2, 1);
        Etape act2 = new Activite("fin du parc", 2, 1);
        Etape act3 = new Activite("parcours gratuit", 2, 1);

        // La suite d'activités
        act1.ajouterSuccesseur(guich);
        guich.ajouterSuccesseur(actRes);
        actRes.ajouterSuccesseur(act2);
        actRes.ajouterSuccesseur(act3);
        act3.ajouterSuccesseur(act2);

        world.ajouter(act1, guich, actRes, act2, act3);

        //entrée
        world.aCommeEntree(act1);

        world.aCommeSortie(act2);


        try {
            ClientTwisk leClient = new ClientTwisk();
            ClassLoaderPerso classLoaderPerso = new ClassLoaderPerso(leClient.getClass().getClassLoader());
            Class<?> laClasse = classLoaderPerso.loadClass("twisk.simulation.Simulation");
            Constructor<?> leConstructeur = laClasse.getConstructor();
            Object laSimulation = leConstructeur.newInstance();
            Method setNbClients = laClasse.getMethod("setNbClients",int.class);
            Method simulation = laClasse.getMethod("simuler", Monde.class);
            setNbClients.invoke(laSimulation, 7);
            simulation.invoke(laSimulation, world);
            //simulation.invoke(laSimulation, world1);// Ajout second monde



        }catch(ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e){
            e.printStackTrace();

        }

        // Second monde ----------------------------------------------------------------------

        Monde world1 = new Monde();
        //Les activités
        Etape act6 = new Activite("Début du ZOO", 5, 3);
        Etape guich1 = new Guichet("Achat des tickets ZOO", 3);
        Etape actRes1 = new ActiviteRestreinte("Visite du ZOO", 5, 3);
        Etape act7 = new Activite("fin du ZOO", 2, 1);
        Etape act8 = new Activite("fin du ZOO2", 2, 1);

        // La suite d'activités
        act6.ajouterSuccesseur(guich1);
        guich1.ajouterSuccesseur(actRes1);
        actRes1.ajouterSuccesseur(act7);
        act7.ajouterSuccesseur(act8);


        world1.ajouter(act6, guich1, actRes1, act7, act8);

        //entrée
        world1.aCommeEntree(act6);
        //Sortie
        world1.aCommeSortie(act8);
        // ---------------------------------------------------------------------------------------


        try {
            ClientTwisk leClient = new ClientTwisk();
            ClassLoaderPerso classLoaderPerso = new ClassLoaderPerso(leClient.getClass().getClassLoader());
            Class<?> laClasse = classLoaderPerso.loadClass("twisk.simulation.Simulation");
            Constructor<?> leConstructeur = laClasse.getConstructor();
            Object laSimulation = leConstructeur.newInstance();
            Method setNbClients = laClasse.getMethod("setNbClients",int.class);
            Method simulation = laClasse.getMethod("simuler", Monde.class);
            setNbClients.invoke(laSimulation, 7);
            simulation.invoke(laSimulation, world1);// Ajout second monde



        }catch(ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e){
            e.printStackTrace();

        }
    }

     */
}
