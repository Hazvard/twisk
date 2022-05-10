package twiskIG.outils;

public class Valider {
    private String delai;
    private String ecart;

    public Valider(String del, String ecart){
        this.delai = del;
        this.ecart = ecart;
    }

    public String getDelai() {
        return delai;
    }

    public String getEcart() {
        return ecart;
    }

    public String getJetonValide(){return "0";};

    @Override
    public String toString() {
        return "Valider{" +
                "delai='" + delai + '\'' +
                ", ecart='" + ecart + '\'' +
                '}';
    }
}
