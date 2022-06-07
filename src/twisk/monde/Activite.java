package twisk.monde;

public class Activite extends Etape {

    protected int temps;
    protected int ecartTemps;

    /**
     * Constructeur
     * @param nom
     */
    public Activite(String nom) {
        super(nom);
        temps = 2;
        ecartTemps = 1;
    }

    /**
     * constructeur
     * @param nom nom de l'activité
     * @param num numéros de l'activité
     */
    public Activite(String nom, int num) {
        super(nom, num);
    }

    /**
     * constructeur
     * @param nom
     * @param t temps que dure l'activité
     * @param e écart que peut plus ou moins prendre un client
     */
    public Activite(String nom, int t, int e){
        super(nom);
        if(t>e){
            temps = t;
            ecartTemps = e;
        }
    }

    /**
     * Constructeur
     * @param nom nom de l'activité
     * @param t temps que dure l'activité
     * @param e écart que peut plus ou moins prendre un client
     * @param num numéros de l'activité
     */
    public Activite(String nom, int t, int e, int num){
        super(nom, num);
        if(t>e){
            temps = t;
            ecartTemps = e;
        }
    }

    /**
     * Fonction qui renvoie le code C le l'activitée
     * @return le code en C
     */
    public String toC(){
        if(this.nombreDeSuccesseurs() < 2) {
            StringBuilder c = new StringBuilder();
            c.append("  delai(" + this.temps + ", " + this.ecartTemps + ");\n");
            c.append("  transfert(" + this.getNumEtape() + ", " + this.gstsuccesseurs.getSuccesseur().getNumEtape() + ");\n\n");


            c.append(this.gstsuccesseurs.getSuccesseur().toC());

            return c.toString();
        }else{
            StringBuilder c = new StringBuilder();

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
            return c.toString();
        }
    }

    public int getTemps() {
        return temps;
    }

    public int getEcartTemps() {
        return ecartTemps;
    }

    /**
     * Permet de savoir si l'activité est une sortie ou non
     * @return
     */
    public boolean estUneSortie(){return false;}

    /**
     * Permet de savoir si l'activité est une activite ou non
     * @return
     */
    @Override
    public boolean estUneActivite() {
        return true;
    }

    @Override
    public String toString() {
        return "Activité : " + super.toString();
    }
}
