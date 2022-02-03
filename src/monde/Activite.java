package monde;

import java.rmi.activation.ActivationID;

public class Activite extends Etape {

    private int temps;
    private int ecartTemps;

    Activite(String nom) {
        super(nom);
    }

    Activite(String nom, int t, int e){
        super(nom);
        temps = t;
        ecartTemps = e;
    }

    @Override
    public boolean estUneActivite() {
        return true;
    }
}
