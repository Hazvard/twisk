package twisk.outils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    void creerFichier(String codeC){



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
}
