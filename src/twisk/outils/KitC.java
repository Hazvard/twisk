package twisk.outils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class KitC {

    public KitC(){

    }

    public void creerEnvironnement(){
        try {
// création du répertoire twisk sous /tmp. Ne déclenche pas d’erreur si le répertoire existe déjà
            Path directories = Files.createDirectories(Paths.get("/tmp/twisk"));
// copie des deux fichiers programmeC.o et def.h depuis le projet sous /tmp/twisk
            String[] liste = {"programmeC.o", "def.h"};
            for (String nom : liste) {
                Path source = Paths.get(getClass().getResource("/codeC/" + nom).getPath());
                Path newdir = Paths.get("/tmp/twisk/");
                Files.copy(source, newdir.resolve(source.getFileName()), REPLACE_EXISTING);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void creerFichier(String codeC){
        File fichier = new File("/tmp/twisk/client.c");
        try {
            if (fichier.createNewFile()) {
                System.out.println("Fichier créé : " + fichier.getName());
            } else {
                System.out.println("Impossible de créé le fichier");
            }
        } catch (IOException e) {
            System.out.println("Erreur dans la lecture.");
            e.printStackTrace();
        }
        try {
            FileWriter texte = new FileWriter("/tmp/twisk/client.c");
            texte.write(codeC);
            texte.close();
            System.out.println("Ecrit dans client.c completed !");
        } catch (IOException e) {
            System.out.println("Erreur dans l'écriture");
            e.printStackTrace();
        }
    }



    public void compiler() {
        Runtime runtime = Runtime.getRuntime();
        try {

            Process p = runtime.exec("gcc -Wall -fPIC -c /tmp/twisk/client.c -o /tmp/twisk/client.o" );


            // récupération des messages sur la sortie standard et la sortie d’erreur de la commande exécutée
            // à reprendre éventuellement et à adapter à votre code

            BufferedReader output = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            String ligne ;

            while ((ligne = output.readLine()) != null)
                System.out.println(ligne);

            while ((ligne = error.readLine()) != null)
                System.out.println(ligne);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void construireLaLibrairie(){
        Runtime runtime = Runtime.getRuntime();
        try{
            //Ligne à faire executée par le programme java pour la création de la librairie
            Process p = runtime.exec("gcc -shared /tmp/twisk/programmeC.o /tmp/twisk/client.o -o /tmp/twisk/libTwisk.so");
            //On attend que la commande se termine avant de passer à la suite
            p.waitFor();

        } catch (IOException e) {
            //Catch pour runtime.exec
            e.printStackTrace();
        } catch (InterruptedException e) {
            //Catch pour waitFor
            e.printStackTrace();
        }
    }
}
