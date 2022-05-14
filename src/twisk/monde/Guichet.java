package twisk.monde;

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
        Etape etapeSuivante = this.gstsuccesseurs.getSuccesseur() ;

        c.append("  P(ids," + this.getNumSemaphore() + ");\n");
        c.append("  transfert(" + this.getNumEtape() + ", " + etapeSuivante.getNumEtape() + ");\n"); // s'il y a un jeton on va dans actRestreinte
        c.append("  delai("+ etapeSuivante.getTemps() + ","+  etapeSuivante.getEcartTemps() + ");\n"); // délai de l'étape restreinte
        c.append("  V(ids, " + this.getNumSemaphore() +");\n"); // On rend le jeton avant de partir dans l'autre étape


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
