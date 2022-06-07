package twisk.monde;

public class ActiviteRestreinte extends Activite{

    /**
     * Constructeur
     * @param nom nom de l'act
     */
    public  ActiviteRestreinte(String nom) {
        super(nom);
    }

    /**
     *  Constructeur
     * @param nom nom nom de l'act
     * @param num numéros nom de l'act
     */
    public  ActiviteRestreinte(String nom, int num) {
        super(nom, num);
    }

    /**
     *
     * @param nom nom nom de l'act
     * @param t durée de l'activité en temps normal
     * @param e écart de temps que prend plus ou moins le client à faire
     */
    public ActiviteRestreinte(String nom, int t, int e) {
        super(nom, t, e);
    }

    /**
     *
     * @param nom nom nom de l'act
     * @param t durée de l'activité en temps normal
     * @param e écart de temps que prend plus ou moins le client à faire
     * @param num numéros nom de l'act
     */
    public ActiviteRestreinte(String nom, int t, int e, int num) {
        super(nom, t, e, num);
    }

    /**
     * Fonction qui renvoie le code C le l'activitée
     * @return le code C
     */
    public String toC(){
        StringBuilder c = new StringBuilder();

        if(this.nombreDeSuccesseurs() < 2){
            c.append(this.gstsuccesseurs.getSuccesseur().toC());
            c.append("  transfert(" + this.getNumEtape() + ", " + this.getGstsuccesseurs().getSuccesseur().getNumEtape() + ");\n\n");

        }else{

            c.append("\n  int aleatoire_etape" + this.getNumEtape() + " = rand() %" + this.nombreDeSuccesseurs() + " ;\n\n");
            c.append("  delai(" + this.temps + ", " + this.ecartTemps + ");\n\n");
            c.append("  switch(aleatoire_etape" + this.getNumEtape() + "){\n");

            int compteur = 0;
            for (Etape etape : gstsuccesseurs) {
                c.append("      case " + compteur+":\n");
                c.append("  transfert(" + this.getNumEtape() + ", " + etape.getNumEtape() + ");\n");
                c.append(etape.toC());
                c.append("  break;\n\n");
                compteur++;
            }

            c.append("  }\n\n");
        }

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
