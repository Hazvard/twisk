package twiskIG.outils;

public class TailleComposant {

    private int x = 1080;
    private int y = 720;
    private int hauteurEtape = 100;
    private int largeurEtape = 300;

    private static TailleComposant instance = new TailleComposant();

/**
 * renvoie l'instance de la fabrique
 */
    static public TailleComposant getInstance(){
        return instance;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getXrand(){
        return (int)(Math.random() * (x - 150));
    }

    public int getYrand(){
        return (int)(Math.random() * (y - 100));
    }

    public int getHauteurEtape() {
        return hauteurEtape;
    }

    public int getLargeurEtape() {
        return largeurEtape;
    }
}
