package twisk_IG.mondeIG;

import twisk_IG.outils.TailleComposants;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public abstract class EtapeIG implements Iterable<PointDeControleIG> {

    private String nom;
    private String identifiant;
    private int posX;
    private int posY;
    private int largeur;
    private int hauteur;
    private int ecart;
    private int delai;
    private boolean entree;
    private boolean sortie;
    private HashMap<String, PointDeControleIG> pointDeControles;


    public EtapeIG(String nom, String identifiant, int hauteur, int largeur){

        this.identifiant = identifiant;
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.nom = nom;
        this.delai = 0;
        this.ecart = 0;
        this.entree = false;
        this.sortie = false;

        TailleComposants taille = TailleComposants.getInstance();

        // On définie la position sur l'écran de manière aléatoire
        Random random = new Random();
        this.posX = taille.getPositionX();
        this.posY = taille.getPositionY();

        pointDeControles = new HashMap<String, PointDeControleIG>();
        pointDeControles.put("haut", new PointDeControleIG(posX + largeur/2, posY, this, "haut"));
        pointDeControles.put("bas", new PointDeControleIG(posX + largeur/2, posY + hauteur, this, "bas"));

        pointDeControles.put("gauche", new PointDeControleIG(posX, posY + hauteur/2, this, "gauche"));
        pointDeControles.put("droite", new PointDeControleIG(posX + largeur, posY + hauteur/2, this, "droite"));

    }

    public Iterator<PointDeControleIG> iterator(){
        return pointDeControles.values().iterator();
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public int getLargeur() {
        return largeur;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public String getNom() {
        return nom;
    }

    public PointDeControleIG getHaut(){
        return pointDeControles.get("haut");
    }

    public PointDeControleIG getBas(){
        return pointDeControles.get("bas");
    }

    public PointDeControleIG getGauche(){
        return pointDeControles.get("gauche");
    }

    public PointDeControleIG getDroite(){
        return pointDeControles.get("droite");
    }


    public boolean estUneActivite(){
        return false;
    }




    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setPos(int x, int y){
        posX = x;
        posY = y;

        pointDeControles = new HashMap<String, PointDeControleIG>();
        pointDeControles.put("haut", new PointDeControleIG(posX + largeur/2, posY, this, "haut"));
        pointDeControles.put("bas", new PointDeControleIG(posX + largeur/2, posY + hauteur, this, "bas"));

        pointDeControles.put("gauche", new PointDeControleIG(posX, posY + hauteur/2, this, "gauche"));
        pointDeControles.put("droite", new PointDeControleIG(posX + largeur, posY + hauteur/2, this, "droite"));

    }

    public int getDelai() {
        return delai;
    }

    public int getEcart() {
        return ecart;
    }

    public void setDelai(int delai) {
        this.delai = delai;
    }

    public void setParametres(int delai, int ecart){
        this.ecart = ecart;
        this.delai = delai;
    }

    public void setEcart(int ecart) {
        this.ecart = ecart;
    }

    public boolean isEntree() {
        return entree;
    }

    public boolean isSortie() {
        return sortie;
    }

    public void setEntree(boolean entree) {
        this.entree = entree;
    }

    public void setSortie(boolean sortie) {
        this.sortie = sortie;
    }
}
