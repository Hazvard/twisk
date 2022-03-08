package twisk.monde;

public class Guichet extends Etape{

    private int nbjetons;
    private int numSemaphore ;

    public Guichet(String nom){
        super(nom);
        nbjetons = 1 ;
    }

    public Guichet(String nom, int num){
        super(nom, num);
        nbjetons = 1 ;
    }

    public Guichet(String nom, int nb, int numSemaphore){
        super(nom);
        nbjetons = nb;
        this.numSemaphore = numSemaphore ;
    }

    public Guichet(String nom, int nb,int num, int numSemaphore){
        super(nom, num);
        nbjetons = nb;
        this.numSemaphore = numSemaphore ;
    }

    public String toC(){
        StringBuilder c = new StringBuilder();
        int suivant = this.getNumEtape() + 1;
        int suivant2 = suivant +1 ;
        c.append("P(ids," + this.numSemaphore + ");\n");
        c.append("transfert(" + this.getNumEtape() + ", " + this.gstsuccesseurs.getSuccesseur().getNumEtape() + ");\n");
        c.append("delai(3,1);\n");
        c.append("transfert(" + suivant + ", " + suivant2 + ");\n");
        c.append("V(ids, " + this.getNumEtape() +");\n");
        return c.toString();
        //int suivant = this.getNumEtape() + 1;
        //int suivant2 = suivant +1 ;
        //return "P(ids,"+this.getNumEtape()+");\n"
        //        + "transfert(" + this.getNumEtape()+ ", " + suivant +");\n" +
        //        "delai(3, 1);\n"
        //        + "transfert(" + suivant + ", " + suivant2 + ");\n"
        //        + "V(ids, " + this.getNumEtape() +");\n";
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
