package monde;

public class Guichet extends Etape{

    private int nbjetons;

    Guichet(String nom){
        super(nom);
        nbjetons = 1 ;
    }

    Guichet(String nom, int nb){
        super(nom);
        nbjetons = nb;
    }

    @Override
    public boolean estUnGuichet() {
        return true;
    }

    @Override
    public String getNom() {
        return super.getNom();
    }

    @Override
    public GestionnaireSuccesseurs getGstsuccesseurs() {
        return super.getGstsuccesseurs();
    }

    public int getNbjetons() {
        return nbjetons;
    }
}
