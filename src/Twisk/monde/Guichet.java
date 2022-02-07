package Twisk.monde;

public class Guichet extends Etape{

    private int nbjetons;

    public Guichet(String nom){
        super(nom);
        nbjetons = 1 ;
    }

    public Guichet(String nom, int nb){
        super(nom);
        nbjetons = nb;
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
}
