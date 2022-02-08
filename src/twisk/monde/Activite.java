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

    @Override
    public boolean estUneActivite() {
        return true;
    }

    @Override
    public String toString() {
        return "Activité : " + super.toString();
    }
}
