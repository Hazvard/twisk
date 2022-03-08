package twisk.monde;

public class Activite extends Etape {

    private int temps;
    private int ecartTemps;

    public Activite(String nom) {
        super(nom);
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
        return "transfert("+this.getNumEtape()+", " + this.getNumEtape() + 1 + ");\n"
                +"delai("+this.temps + ", " + this.ecartTemps + ");";
    }

    @Override
    public boolean estUneActivite() {
        return true;
    }

    @Override
    public String toString() {
        return "Activité : " + super.toString();
    }
}
