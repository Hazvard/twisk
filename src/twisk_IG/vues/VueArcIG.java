package twisk_IG.vues;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import twisk_IG.mondeIG.ArcIG;
import twisk_IG.mondeIG.MondeIG;
import twisk_IG.mondeIG.PointDeControleIG;

import java.awt.*;

public class VueArcIG extends Group {

    protected MondeIG mondeIG;
    protected ArcIG arc;
    protected Line ligne;
    protected Polyline triangle;
    protected Boolean selection;

    public VueArcIG(MondeIG mondeIG, ArcIG arcIg){
        this.mondeIG = mondeIG;
        arc = arcIg;
        selection = false;

        ligne = new Line(arc.poxAX(), arc.poxAY(), arc.poxBX(), arc.poxBY());
        ligne.setStrokeWidth(5);
        ligne.setStroke(Color.rgb(190, 194, 190));

        double a = arc.poxBX() - ((arc.poxBX() - arc.poxAX())*0.15);
        double b = arc.poxBY() - ((arc.poxBY() - arc.poxAY())*0.15);

        triangle = new Polyline();
        Double x1,y1,x2,y2;

            x2 = this.arc.poxAX();
            y2 = this.arc.poxAY();
            x1 = this.arc.poxBX();
            y1 = this.arc.poxBY();


        int taillePointe = 30;
        int radian = 30;

        double theta = Math.atan2(y2-y1, x2-x1);
        double pt1x = x1 + Math.cos(theta + Math.toRadians(radian)) * taillePointe;
        double pt1y = y1 + Math.sin(theta + Math.toRadians(radian)) * taillePointe;
        double pt2x = x1 + Math.cos(theta - Math.toRadians(radian)) * taillePointe;
        double pt2y = y1 + Math.sin(theta - Math.toRadians(radian)) * taillePointe;

        triangle.getPoints().addAll(new Double[]{
                pt1x,pt1y,
                pt2x,pt2y,
                x1,y1,
        });


        triangle.setStrokeWidth(0);
        triangle.setStroke(Color.rgb(190, 194, 190));
        triangle.setFill(Color.rgb(190, 194, 190));
        this.getChildren().addAll(ligne, new Group(triangle));









        this.setOnMouseClicked(event ->{
            mondeIG.selection(this.arc);

            this.getChildren().clear();

            if(selection){
                selection = false;


                ligne.setStrokeWidth(5);
                ligne.setStroke(Color.rgb(190, 194, 190));


                triangle = new Polyline();

                triangle.getPoints().addAll(new Double[]{
                        pt1x,pt1y,
                        pt2x,pt2y,
                        x1,y1,
                });


                triangle.setStrokeWidth(0);
                triangle.setStroke(Color.rgb(190, 194, 190));
                triangle.setFill(Color.rgb(190, 194, 190));
                this.getChildren().addAll(ligne, new Group(triangle));


            }else {
                selection = true;



                ligne.setStrokeWidth(5);
                ligne.setStroke(Color.rgb(201, 16, 3));


                triangle = new Polyline();

                triangle.getPoints().addAll(new Double[]{
                        pt1x,pt1y,
                        pt2x,pt2y,
                        x1,y1,
                });


                triangle.setStrokeWidth(0);
                triangle.setStroke(Color.rgb(201, 16, 3));
                triangle.setFill(Color.rgb(201, 16, 3));
                this.getChildren().addAll(ligne, new Group(triangle));

            }

        });
    }
}
