package monde;

public class Monde {

    protected GestionnaireEtape gstEtape;
    protected SasEntree entree ;
    protected SasSortie sortie ;

    @Override
    public String toString() {
        return "Monde{" +
                "gstEtape=" + gstEtape +
                ", entree=" + entree +
                ", sortie=" + sortie +
                '}';
    }
}
