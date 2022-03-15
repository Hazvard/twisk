package twisk.simulation;
import twisk.monde.*;
import twisk.outils.KitC;

public class Simulation{
    private KitC kitC;

    public Simulation(){
        kitC = new KitC();
        kitC.creerEnvironnement();
    }

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
    }

    public static void main(String[] args) {
        Monde world = new Monde();
        Simulation sim = new Simulation();
        sim.simuler(world);
    }
}