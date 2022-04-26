package twisk.monde;

import test.outils.FabriqueNuméroTest;
import twisk.outils.FabriqueNumero;

public class Guichet extends Etape{

    private int nbjetons;
    private int numSemaphore ;

    public Guichet(String nom){
        super(nom);
        nbjetons = 1;
        FabriqueNumero fab = FabriqueNumero.getInstance();
        numSemaphore = fab.getCptSemaphore();
    }

    public Guichet(String nom, int nbjetons){
        super(nom);
        this.nbjetons = nbjetons ;
        FabriqueNumero fab = FabriqueNumero.getInstance();
        numSemaphore = fab.getCptSemaphore();
    }

    public Guichet(String nom, int nbJetons, int numSemaphore){
        super(nom);
        this.nbjetons = nbJetons;
        this.numSemaphore = numSemaphore ;
    }

    public Guichet(String nom, int nbJetons,int num, int numSemaphore){
        super(nom, num);
        this.nbjetons = nbJetons;
        this.numSemaphore = numSemaphore ;
    }

    public String toC(){
        StringBuilder c = new StringBuilder();
        int suivant = this.getNumEtape() + 1;
        int suivant2 = suivant +1 ;
        Etape etapeNext = this.getGstsuccesseurs().getSuccesseur();
        c.append("  P(ids," + this.getNumSemaphore() + ");\n");
        c.append("  transfert(" + this.getNumEtape() + ", " + this.gstsuccesseurs.getSuccesseur().getNumEtape() + ");\n");
        //delai de l'étape d'après et l'écart de l'étape d'après ???
        c.append("  delai("+ etapeNext.getTemps() + ","+  etapeNext.getEcartTemps() + ");\n");
        c.append("  V(ids, " + this.getNumSemaphore() +");\n\n");   //On rend le jeton avant transfert
        c.append("  transfert(" + etapeNext.getNumEtape() + ", " + etapeNext.getGstsuccesseurs().getSuccesseur().getNumEtape() + ");\n");
        c.append(this.gstsuccesseurs.getSuccesseur().toC());
        return c.toString();
    }

    @Override
    public boolean estUnGuichet() {
        return true;
    }

    public int getNbjetons() {
        return nbjetons;
    }

    @Override
    public String toString() {
        return "Guichet : " + super.toString();
    }

    public int getNumSemaphore() {
        return numSemaphore;
    }

    public void setNumSemaphore(int numSemaphore) {
        this.numSemaphore = numSemaphore;
    }
}
