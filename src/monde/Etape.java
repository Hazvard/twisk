package monde;

public abstract class Etape implements Iterable{
    private String nom;
    protected GestionnaireSuccesseurs Gstsuccesseurs;

    public Etape(String nom){
        this.nom = nom;
    }
}
