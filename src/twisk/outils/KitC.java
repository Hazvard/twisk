package twisk.outils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class KitC {

    /**
     * Constructeur
     */
    public KitC(){}

    /**
     * Va créer les dossiers ou vont être créer les fichier néccessaires pour le C
     */
    public void creerEnvironnement(){
        try {
            // création du répertoire twisk sous /tmp. Ne déclenche pas d’erreur si le répertoire existe déjà
            Path directories = Files.createDirectories(Paths.get("/tmp/twisk"));
            // copie des deux fichiers programmeC.o et def.h depuis le projet sous /tmp/twisk
            String[] liste = {"programmeC.o", "def.h", "codeNatif.o"};
            for (String nom : liste) {
                InputStream source = getClass().getResource("/codeC/" + nom).openStream();
                File destination = new File("/tmp/twisk/" + nom);
                copier(source, destination);
                //Path source = Paths.get(getClass().getResource("/codeC/" + nom).getPath());
                //Path newdir = Paths.get("/tmp/twisk/");
                //Files.copy(source, newdir.resolve(source.getFileName()), REPLACE_EXISTING);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Copie le premier paramètre dans le second
     * @param source
     * @param desti
     * @throws IOException
     */
    private void copier(InputStream source, File desti) throws IOException{
        InputStream sourceFile = source;
        OutputStream destinationFile = new FileOutputStream(desti);

        //Lect par segement de 0.5 Mo
        byte buffer[] = new byte[512*1024];
        int nbLecture;
        while((nbLecture = sourceFile.read(buffer)) != -1){
            destinationFile.write(buffer, 0, nbLecture);
        }
        destinationFile.close();
        sourceFile.close();
    }

    /**
     * Créer le fichier en C
     * @param codeC
     */
    public void creerFichier(String codeC){
        File fichier = new File("/tmp/twisk/client.c");
        try {
            if(fichier.exists()){
                fichier.delete();
            }
            if (fichier.createNewFile()) {
                //System.out.println("Fichier créé : " + fichier.getName());
            } else {
                //System.out.println("Impossible de créé le fichier");
            }
        } catch (IOException e) {
            //System.out.println("Erreur dans la lecture.");
            e.printStackTrace();
        }
        try {
            FileWriter texte = new FileWriter("/tmp/twisk/client.c");
            texte.write(codeC);
            texte.close();
            //System.out.println("Ecrit dans client.c completed !");
        } catch (IOException e) {
            //System.out.println("Erreur dans l'écriture");
            e.printStackTrace();
        }
    }


    /**
     * Compile le fichier C
     */
    public void compiler() {
        Runtime runtime = Runtime.getRuntime();
        try {

            Process p = runtime.exec("gcc -Wall -fPIC -c /tmp/twisk/client.c -o /tmp/twisk/client.o" );


            // récupération des messages sur la sortie standard et la sortie d’erreur de la commande exécutée
            // à reprendre éventuellement et à adapter à votre code

            BufferedReader output = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            String ligne ;

            while ((ligne = output.readLine()) != null);
                //System.out.println(ligne);

            while ((ligne = error.readLine()) != null);
                //System.out.println(ligne);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Construit la lib en fonction du C et du numéro du monde
     * @param numMonde
     */
    public void construireLaLibrairie(int numMonde){
        Runtime runtime = Runtime.getRuntime();

        try{
            //Ligne à faire executée par le programme java pour la création de la librairie
            Process p = runtime.exec("gcc -shared /tmp/twisk/programmeC.o /tmp/twisk/codeNatif.o /tmp/twisk/client.o -o /tmp/twisk/libTwisk"+numMonde+".so");
            BufferedReader output = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            //On attend que la commande se termine avant de passer à la suite
            p.waitFor();
            String ligne;
            while((ligne = output.readLine()) != null){
                //System.out.println(ligne);
            }
            while (((ligne = error.readLine())!= null)){
                //System.out.println(ligne);
            }
            //System.out.println("");

        } catch (IOException e) {
            //Catch pour runtime.exec
            e.printStackTrace();
        } catch (InterruptedException e) {
            //Catch pour waitFor
            e.printStackTrace();
        }
    }
}
