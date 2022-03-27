package twisk.monde;

public class ActiviteRestreinte extends Activite{

    public  ActiviteRestreinte(String nom) {
        super(nom);
    }

    public  ActiviteRestreinte(String nom, int num) {
        super(nom, num);
    }

    public ActiviteRestreinte(String nom, int t, int e) {
        super(nom, t, e);
    }

    public ActiviteRestreinte(String nom, int t, int e, int num) {
        super(nom, t, e, num);
    }

    public String toC(){
        StringBuilder c = new StringBuilder();

        c.append(this.gstsuccesseurs.getSuccesseur().toC());
        return c.toString();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean estUneActiviteRes() {
        return true;
    }
}
