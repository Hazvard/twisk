package twisk.monde;

public class SasSortie extends Activite {

    public SasSortie() {
        super("Sortie");
    }

    public SasSortie(int num) {
        super("Sortie", num);
    }

    public String toC(){
        return "";
    }

    @Override
    public String toString() {
        return super.toString() + getNom();
    }

}
