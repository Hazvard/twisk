package twisk.monde;

public class SasEntree extends Activite{

    public SasEntree() {
        super("Entrée");
    }

    public SasEntree(int num) {
        super("Entrée", num);
    }

    public String toC(){
        return "entrer("+getNumEtape() +");\n" +
                "transfert("+getNumEtape()+", "+(getNumEtape()+1)+");\n";
    }

    @Override
    public String toString() {
        return super.toString();
    }
}