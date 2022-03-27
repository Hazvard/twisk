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
        Etape act1 = new Activite("Début du parc", 5, 3);
        Etape guich = new Guichet("Achat des tickets");
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

        String Cworld = world.toC();

        kitC.creerFichier(Cworld);
        kitC.compiler();
        kitC.construireLaLibrairie();

        System.load("/tmp/twisk/libTwisk.so") ; // Ajout séance 6

        int nbEtape = world.nbEtapes();
        int nbClient = 4;
        int nbGuichet = world.nbGuichets();
        int[] tabJetonGuichet = {2};
        boolean flag = true;

        int[] tabSimu = start_simulation(nbEtape, nbGuichet, nbClient, tabJetonGuichet);
        System.out.println("Les Clients : " + tabSimu[0]  + " " +  tabSimu[1]  +" " + tabSimu[2]  +  " " + tabSimu[3]  + " ");

        while (flag){

            int i = 0;
            int[] tabClient = ou_sont_les_clients(nbEtape, nbClient);
            for(Etape etape: world){
                if(etape.isEtapeEntree()){
                    System.out.println("Entrée : " + tabClient[i * (nbClient+1)]);
                }else if(etape.isEtapeSortie()){
                    System.out.println("Sortie : " + tabClient[i * (nbClient+1)]);
                }else if(etape.estUnGuichet()){
                    System.out.println("Guichet : " + tabClient[i * (nbClient+1)]);
                }else if(etape.estUneActivite()){
                    System.out.println("Activité : " + tabClient[i * (nbClient+1)]);
                }
                i++;

            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(nbClient == tabClient[nbClient+1])
                flag = false ;

            System.out.println();
        }
        nettoyage();
















    }



    public static void main(String[] args) {
        Monde world = new Monde();
        Simulation sim = new Simulation();
        sim.simuler(world);
    }
}