package Twisk.monde;

public class SasSortie extends Activite {

    public SasSortie() {
        super("Sortie");
    }

    @Override
    public String toString() {
        return super.toString() + getNom();
    }

}
