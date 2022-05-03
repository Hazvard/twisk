package twisk_IG.outils;

import java.util.Random;

public class TailleComposants {


    private int LargeurEcran = 1200;
    private int HauteurEcran = 840;

    private int LargeurActivite = 250;
    private int HauteurActivite = 100;

    private int LargeurBoxAct = 230;
    private int HauteurBoxAct = 70;



    private static TailleComposants instance = new TailleComposants() ;

    static public TailleComposants getInstance(){
        return instance;
    }


    public int getLargeurEcran() {
        return LargeurEcran;
    }

    public int getHauteurEcran() {
        return HauteurEcran;
    }

    public int getLargeurActivite() {
        return LargeurActivite;
    }

    public int getHauteurActivite() {
        return HauteurActivite;
    }

    public int getHauteurBoxAct() {
        return HauteurBoxAct;
    }

    public int getLargeurBoxAct() {
        return LargeurBoxAct;
    }

    public int getPositionX(){
        Random random = new Random();
        return random.nextInt((LargeurEcran - LargeurActivite - 40))+20;
    }

    public int getPositionY(){
        Random random = new Random();
        return random.nextInt((HauteurEcran - HauteurActivite - 40))+20;
    }
}
