package twisk_IG.mondeIG;

import twisk_IG.outils.FabriqueIdentifiant;

public class ArcIG {

    private PointDeControleIG pointA;
    private PointDeControleIG pointB;
    private EtapeIG depart;
    private EtapeIG fin;
    private String identifiant;

    public ArcIG(PointDeControleIG a, PointDeControleIG b){
        FabriqueIdentifiant fabriqueIdentifiant = FabriqueIdentifiant.getInstance();
        String idt = fabriqueIdentifiant.getIdentifiant();
        identifiant = idt;
        pointA = a;
        pointB = b;
        depart = pointA.getEtape();
        fin = pointB.getEtape();
    }


    public void setPointA(PointDeControleIG pointA) {
        this.pointA = pointA;
    }

    public void setPointB(PointDeControleIG pointB) {
        this.pointB = pointB;
    }

    public double poxAX(){
        return pointA.getPosX();
    }

    public double poxAY(){
        return pointA.getPosY();
    }

    public double poxBY(){
        return pointB.getPosY();
    }

    public double poxBX(){
        return pointB.getPosX();
    }

    public EtapeIG getDepart() {
        return depart;
    }

    public EtapeIG getFin() {
        return fin;
    }

    public String getIDDepart(){
        return depart.getIdentifiant();
    }

    public String getIDFin(){
        return fin.getIdentifiant();
    }

    public String getIDB(){
        return pointB.getIdentifiant();
    }

    public String getIDA(){
        return pointA.getIdentifiant();
    }


    public String getIdentifiant() {
        return identifiant;
    }
}
