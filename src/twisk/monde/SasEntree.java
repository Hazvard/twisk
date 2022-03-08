package twisk.monde;

public class SasEntree extends Activite{

    public SasEntree() {
        super("Entrée");
    }

    public SasEntree(int num) {
        super("Entrée", num);
    }

    public String toC(){
        StringBuilder c = new StringBuilder();
        c.append("\n  entrer("+getNumEtape() +");\n");
        c.append("  transfert("+getNumEtape()+", "+this.gstsuccesseurs.getSuccesseur().getNumEtape()+");// L'entrée transfere à la première act\n\n");
        c.append(this.gstsuccesseurs.getSuccesseur().toC());
        return c.toString();



    }

    @Override
    public String toString() {
        return super.toString();
    }
}