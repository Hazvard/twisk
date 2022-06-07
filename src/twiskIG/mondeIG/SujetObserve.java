package twiskIG.mondeIG;

import twiskIG.vues.Observateur;
import java.util.ArrayList;

public abstract class SujetObserve {
    private ArrayList<Observateur> obs;

    public SujetObserve(){
        obs = new ArrayList<>(10);
    }

    public void ajouterObservateur(Observateur obs){
        this.obs.add(obs);
    }

    public void notifierObservateur(){
        for (Observateur o: obs
        ) {
            o.reagir();
        }
    }

    public abstract void reagir();
}
