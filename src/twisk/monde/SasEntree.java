package twisk.monde;

public class SasEntree extends Activite{
    /**
     * Constructeur
     */
    public SasEntree() {
        super("Entrée");
    }

    /**
     * Constructeur
     * @param num numéros d'étape
     */
    public SasEntree(int num) {
        super("Entrée", num);
    }

    /**
     * Fonction qui renvoie le code C le l'activitée
     * @return le code C
     */
    public String toC(){
        StringBuilder c = new StringBuilder();
        c.append("\n  entrer("+getNumEtape() +");\n");
        c.append("  delai("+this.temps +", " + this.ecartTemps + ");\n");
        c.append("  transfert("+getNumEtape()+", "+this.gstsuccesseurs.getSuccesseur().getNumEtape()+");// L'entrée transfere à la première act\n\n");
        c.append(this.gstsuccesseurs.getSuccesseur().toC());
        return c.toString();



    }

    @Override
    public String toString() {
        return super.toString();
    }
}