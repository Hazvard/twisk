package monde;

public abstract class Etape implements Iterable{
    private String nom;

    public Etape(String nom){
        this.nom = nom;
    }
}
