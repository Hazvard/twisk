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
        return "P(ids,"+this.getNumEtape()+");\n"
                + "transfert(" + this.getNumEtape() + ", " + this.getNumEtape() + 1 + ");\n"
                + "delai(3, 1)\n"
                + "V(ids, " + this.getNumEtape() +");\n";
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
