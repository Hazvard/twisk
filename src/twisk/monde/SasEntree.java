package twisk.monde;

public class SasEntree extends Activite{

    public SasEntree() {
        super("Entrée");
    }

    public SasEntree(int num) {
        super("Entrée", num);
    }

    public String toC(){
        return "void simulation(int ids){\n"
                + "entrer(0);\n";
    }

    @Override
    public String toString() {
        return super.toString();
    }
}