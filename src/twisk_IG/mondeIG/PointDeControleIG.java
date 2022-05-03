package twisk_IG.mondeIG;

import twisk_IG.outils.FabriqueIdentifiant;

public class PointDeControleIG {

    private double posX ;
    private double posY ;
    private String identifiant ;
    private EtapeIG etape ;


    public PointDeControleIG(double x, double y, EtapeIG etapeIG, String identifiant){
        this.identifiant = identifiant;
        etape = etapeIG;
        posX = x;
        posY = y;

    }

    public PointDeControleIG(double x, double y){
        FabriqueIdentifiant fabriqueIdentifiant = FabriqueIdentifiant.getInstance();
        this.identifiant = fabriqueIdentifiant.getIdentifiant();
        etape = new ActiviteIG("nom", "identifant", 2, 2) ;
        posX = x;
        posY = y;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public EtapeIG getEtape() {
        return etape;
    }
}
