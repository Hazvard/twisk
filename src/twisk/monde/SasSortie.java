package twisk.monde;

public class SasSortie extends Activite {

    public SasSortie() {
        super("Sortie");
    }

    public SasSortie(int num) {
        super("Sortie", num);
    }

    public String toC(){
        return "    //Sortie\n";
    }

    @Override
    public String toString() {
        return super.toString() + getNom();
    }

    @Override
    public boolean estUneSortie(){return true;}
}
