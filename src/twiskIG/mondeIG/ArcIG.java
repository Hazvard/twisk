package twiskIG.mondeIG;

public class ArcIG {
    private PointDeControlIG p1;
    private PointDeControlIG p2;
    private String identifiantDebut ;
    private String identifiantFin ;
    private boolean estSelectionner;




    public ArcIG(PointDeControlIG point1, PointDeControlIG point2){
        p1 = point1;
        p2 = point2;
        identifiantDebut = point1.getEtape().getIdentifiant();
        identifiantFin = point2.getEtape().getIdentifiant();
        estSelectionner = false;
    }

    public boolean isSelectionner() {
        return estSelectionner;
    }

    public void selectionnerArc(){
        estSelectionner = true;
    }

    public void deselectionnerArc(){
        estSelectionner = false;
    }

    public int getPt1X(){
        return p1.getX();
    }
    public int getPt2X(){
        return p2.getX();
    }
    public int getPt1Y(){
        return p1.getY();
    }
    public int getPt2Y(){
        return p2.getY();
    }
    public PointDeControlIG getP1() {
        return p1;
    }
    public PointDeControlIG getP2() {
        return p2;
    }

    @Override
    public String toString() {
        return "ArcIG{" +
                "p1=" + p1 +
                ", p2=" + p2 +
                '}';
    }

    public String getIdentifiantDebut() {
        return identifiantDebut;
    }

    public String getIdentifiantFin() {
        return identifiantFin;
    }
}
