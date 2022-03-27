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
        String Cworld = world.toC();

        kitC.creerFichier(Cworld);
        kitC.compiler();
        kitC.construireLaLibrairie();

        System.load("/tmp/twisk/libTwisk.so") ; // Ajout séance 6

        int nbEtape = world.nbEtapes();
        int nbClient = 7;
        int nbGuichet = world.nbGuichets();
        int[] tabJetonGuichet = new int[nbGuichet];

        int i = 0;
        for(Etape etape: world){
            if(etape.estUnGuichet()){
                Guichet guichetTemp = (Guichet) etape;
                tabJetonGuichet[i] = guichetTemp.getNbjetons();
                i++;
            }
        }

        boolean flag = true;

        int[] tabSimu = start_simulation(nbEtape, nbGuichet, nbClient, tabJetonGuichet);
        System.out.println("Les Clients : " + tabSimu[0]  + " " +  tabSimu[1]  +" " + tabSimu[2]  +  " " + tabSimu[3]  + " ");

        while (flag){

            i = 0;
            int[] tabClient = ou_sont_les_clients(nbEtape, nbClient);
            for(Etape etape: world){
                if(etape.isEtapeEntree()){
                    System.out.println("Entrée : "+ etape.getNom() + " ===>  " + tabClient[i * (nbClient+1)]);
                }else if(etape.isEtapeSortie()){
                    System.out.println("Sortie : "+etape.getNom() +" ===>  " + tabClient[i * (nbClient+1)]);
                }else if(etape.estUnGuichet()){
                    System.out.println("Guichet : "+ etape.getNom() +" ===>  " + tabClient[i * (nbClient+1)]);
                }else if(etape.estUneActivite()){
                    System.out.println("Activité : "+etape.getNom() + " ===>  " + tabClient[i * (nbClient+1)]);
                }
                System.out.print("Clients : " + tabClient[i*(nbClient + 1)]);
                for(int j = 0; j < tabClient[i*(nbClient + 1)]; j++) {
                        System.out.print("   " + tabClient[j + (nbClient * i) + i + 1]);
                }
                i++;
                System.out.println("\n");
            }
            System.out.println("");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(nbClient == tabClient[nbClient+1])
                flag = false ;

            System.out.println();
        }
        nettoyage();
    }
}