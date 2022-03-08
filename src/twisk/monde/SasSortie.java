package twisk.monde;

public class SasSortie extends Activite {

    public SasSortie() {
        super("Sortie");
    }

    public SasSortie(int num) {
        super("Sortie", num);
    }

    public String toC(){
        return "delai(3, 1);\n"
                + "transfert(" + (this.getNumEtape() - 1) + ", " + this.getNumEtape() + ");\n";
    }

    @Override
    public String toString() {
        return super.toString() + getNom();
    }

}
