package twisk.simulation;
import twisk.monde.*;
import twisk.outils.KitC;

public class Simulation{
    private KitC kitC;

    public Simulation(){
        kitC = new KitC();
        kitC.creerEnvironnement();

    }

    public native int[] start_simulation(int nbEtapes, int nbGuichets, int nbClients, int[] tabJetonsGuichet);
    public native int[] ou_sont_les_clients(int nbEtapes, int nbClients);
    public native void nettoyage();

    public void simuler(Monde world){

        //Les activités
        Etape act1 = new Activite("Début du parc");
        Etape guich = new Guichet("Achat des tickets");
        Etape actRes = new ActiviteRestreinte("Visite du parc");
        Etape act2 = new Activite("fin du parc");
        Etape act3 = new Activite("fin du parc2");




        //entrée
        world.aCommeEntree(act1);

        // La suite d'activités
        act1.ajouterSuccesseur(guich);
        guich.ajouterSuccesseur(actRes);
        actRes.ajouterSuccesseur(act2);
        act2.ajouterSuccesseur(act3);

        //Sortie
        world.aCommeSortie(act3);

        String Cworld = world.toC();

        System.out.println(Cworld);
        System.load("/tmp/twisk/libTwisk.so") ; // Ajout séance 6

        int[] tabJetons = {2};
        boolean flag = true;
        int nbClients = 4;
        int nbEtapes = 5;

        start_simulation(nbEtapes, 1, nbClients, tabJetons);



        int[] tabSimu = ou_sont_les_clients(nbEtapes, nbClients);
        System.out.println("\nLes clients : " + tabSimu[0] + " " + tabSimu[1] + " " + tabSimu[2] + " " + tabSimu[3] + "\n\n");


        while(flag){
            int[] tabClient = ou_sont_les_clients(nbEtapes, nbClients);

            for(int i = 0; i < 4; i++){
                System.out.print("Etape " +i + " (");

                switch (i){
                    case 0 :
                        System.out.print("Entree");
                        break;

                    case 4 :
                        System.out.print("Sortie");
                        break;

                    default :
                        System.out.print("Activite");
                        break;


                }

                System.out.println(") "+ tabClient[ i * (4 + 1)] +" client(s) ");


                for(int j = 0; j < tabClient[i*(nbClients + 1)]; j++){
                    System.out.println(" " + tabClient[j + (nbClients * i)+ i + 1]);
                }


                System.out.println("\n");

            }


            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("\n");
            if(4 == tabClient[ (nbEtapes-1) * (nbClients + 1)])
                flag = false ;


        }
        nettoyage();

    }

    public static void main(String[] args) {
        Monde world = new Monde();
        Simulation sim = new Simulation();
        sim.kitC.construireLaLibrairie();
        sim.simuler(world);
        sim.kitC.creerFichier(world.toC());
        sim.kitC.compiler();

    }
}