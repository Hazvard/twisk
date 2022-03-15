package twisk.outils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class KitC {

    public KitC() {

    }

    public void creerEnvironnement() {
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

    void creerFichier(String codeC) throws IOException {
        File fichier = new File("tmp/twisk/client.c");
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
            FileWriter texte = new FileWriter("tmp/twisk/client.c");
            texte.write(codeC);
            texte.close();
            System.out.println("Ecrit dans client.c completed !");
        } catch (IOException e) {
            System.out.println("Erreur dans l'écriture");
            e.printStackTrace();
        }
    }
}