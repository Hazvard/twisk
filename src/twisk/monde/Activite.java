package twisk.monde;

public class Activite extends Etape {

    private int temps;
    private int ecartTemps;

    public Activite(String nom) {
        super(nom);
        temps = 1;
        ecartTemps = 2;
    }

    public Activite(String nom, int num) {
        super(nom, num);
    }

    public Activite(String nom, int t, int e){
        super(nom);
        temps = t;
        ecartTemps = e;
    }

    public Activite(String nom, int t, int e, int num){
        super(nom, num);
        temps = t;
        ecartTemps = e;
    }

    public String toC(){
        StringBuilder c = new StringBuilder();
        c.append("  delai("+this.temps +", " + this.ecartTemps + ");\n");
        c.append("  transfert(" + this.getNumEtape() + ", " + this.gstsuccesseurs.getSuccesseur().getNumEtape() + ");\n\n");

        c.append(this.gstsuccesseurs.getSuccesseur().toC());

        return c.toString();
        // return "delai("+this.temps +", " + this.ecartTemps + ");\ntransfert("+this.getNumEtape()+", " + suivant + ");\n";
    }

    public int getTemps() {
        return temps;
    }

    public int getEcartTemps() {
        return ecartTemps;
    }

    public boolean estUneSortie(){return false;}

    @Override
    public boolean estUneActivite() {
        return true;
    }

    @Override
    public String toString() {
        return "Activité : " + super.toString();
    }
}
