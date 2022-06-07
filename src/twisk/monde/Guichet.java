package twisk.monde;

import twisk.outils.FabriqueNumero;

public class Guichet extends Etape{

    private int nbjetons;
    private int numSemaphore ;

    /**
     * Constructeur
     * @param nom nom de l'étape
     */
    public Guichet(String nom){
        super(nom);
        nbjetons = 1;
        FabriqueNumero fab = FabriqueNumero.getInstance();
        numSemaphore = fab.getCptSemaphore();
    }

    /**
     * Constructeur
     * @param nom nom de l'étape
     * @param nbjetons nombre de jetons disponible
     */
    public Guichet(String nom, int nbjetons){
        super(nom);
        this.nbjetons = nbjetons ;
        FabriqueNumero fab = FabriqueNumero.getInstance();
        numSemaphore = fab.getCptSemaphore();
    }

    /**
     * Constructeur
     * @param nom nom de l'étape
     * @param nbJetons nombre de jetons disponible
     * @param numSemaphore numéros d'identifiants
     */
    public Guichet(String nom, int nbJetons, int numSemaphore){
        super(nom);
        this.nbjetons = nbJetons;
        this.numSemaphore = numSemaphore ;
    }

    /**
     *
     * @param nom nom de l'étape
     * @param nbJetons nombre de jetons disponible
     * @param num numéros d'étape
     * @param numSemaphore numéros d'identifiants
     */
    public Guichet(String nom, int nbJetons,int num, int numSemaphore){
        super(nom, num);
        this.nbjetons = nbJetons;
        this.numSemaphore = numSemaphore ;
    }
    /**
     * Fonction qui renvoie le code C le l'activitée
     * @return le code C
     */
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

    /**
     * renvoie true lorsque l'étape est un guichet
     * @return true s'il s'agit d'un guichet
     */
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
