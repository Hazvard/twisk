package twiskIG.mondeIG;

import twiskIG.outils.FabriqueIdentifiant;

public class PointDeControlIG {
    private int x;
    private int y;
    private String id;
    private EtapeIG etape;
    private boolean estSelec;

    /**
     * Constructeur
     * @param et
     */
    public PointDeControlIG(EtapeIG et) {
        this.etape = et;
        FabriqueIdentifiant fab = FabriqueIdentifiant.getInstance();
        id = fab.getIdentifiantPointDeControl();
        estSelec = false;
    }

    /**
     * identifie l'origine du point de controle
     * @param etape
     * @return
     */
    public boolean estDeLaMemeEtape(EtapeIG etape){
        if(this.etape == etape){
            return true;
        }
        return false;
    }

    public void setEstSelec(){
        estSelec = true;
    }

    public void deselectionner(){
        estSelec = false;
    }

    public boolean isEstSelec(){
        return estSelec;
    }

    public EtapeIG getEtape() {
        return etape;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "PointDeControlIG{" +
                "x=" + x +
                ", y=" + y +
                ", id='" + id + '\'' +
                "}\n";
    }
}
