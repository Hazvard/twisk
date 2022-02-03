package monde;

public class Guichet extends Etape{

    private int nbjetons;

    Guichet(String nom){
        super(nom);
    }

    Guichet(String nom, int nb){
        super(nom);
        nbjetons = nb;
    }

    @Override
    public boolean estUnGuichet() {
        return true;
    }
}
