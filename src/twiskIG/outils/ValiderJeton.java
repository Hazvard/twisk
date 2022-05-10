package twiskIG.outils;

public class ValiderJeton extends Valider {
    private String jetons;

    public ValiderJeton(String jeton){
        super("rien","rien");
        this.jetons = jeton;
    }

    @Override
    public String getJetonValide() {
        return jetons;
    }



    @Override
    public String toString() {
        return "Valider{" +
                "nbJetons='" + jetons + '\'' +
                '}';
    }
}
