package twisk.simulation;
import twisk.monde.*;

public class Simulation{

    public Simulation(){
        //Constructeur vide
    }

    public void simuler(Monde world){
        SasEntree sasEnter = new SasEntree();
        sasEnter.ajouterSuccesseur(new Activite("EntrerDuPark"));
        world.aCommeEntree(sasEnter);

        SasSortie sasExit = new SasSortie();
        sasExit.ajouterSuccesseur(new Activite("SortieDuPark"));
        world.aCommeSortie(sasExit);

        world.ajouter(new Activite("Promenade"), new Guichet("Attente manège"), new Activite("Manège"));

        //world.toC();

        System.out.println(world);
    }

    public static void main(String[] args) {
        Monde world = new Monde();
        Simulation sim = new Simulation();
        sim.simuler(world);
    }
}