package twiskIG.mondeIG;

import twiskIG.outils.TailleComposant;

import java.util.Arrays;
import java.util.Iterator;

public abstract class EtapeIG implements Iterable<PointDeControlIG> {
    private String nom;
    private String identifiant;
    private int posX;
    private int posY;
    private int largeur;
    private int hauteur;
    private int delai;
    private int ecart;
    private PointDeControlIG[] pdc;
    private boolean estSelect;
    private boolean estUneEntre;
    private boolean estUneSortie;


    public EtapeIG(String nom, String idf, int larg, int haut){
        this.nom = nom;
        this.identifiant = idf;
        this.largeur = larg;
        this.hauteur = haut;
        this.estSelect = false;
        this.estUneEntre = false;
        this.estUneSortie = false;
        delai = 4;
        ecart = 2;
        TailleComposant taille = TailleComposant.getInstance();
        posX = taille.getXrand();
        posY = taille.getYrand();
        if(this.isUnGuichet()){
            pdc = new PointDeControlIG[2];
            for(int i = 0; i < 2; i++){
                pdc[i] = new PointDeControlIG(this);
            }
            //Gauche
            pdc[0].setX(posX);
            pdc[0].setY(posY + (largeur / 2));
            //Droite
            pdc[1].setX(posX + hauteur);
            pdc[1].setY(posY + largeur / 2);
        }
        else {
            pdc = new PointDeControlIG[4];
            for(int i = 0; i < 4; i++){
                pdc[i] = new PointDeControlIG(this);
            }
            //Haut
            pdc[0].setX(posX + hauteur / 2);
            pdc[0].setY(posY);
            //Gauche
            pdc[1].setX(posX);
            pdc[1].setY(posY + (largeur / 2));
            //Bas
            pdc[2].setX(posX + hauteur / 2);
            pdc[2].setY(posY + largeur);
            //Droite
            pdc[3].setX(posX + hauteur);
            pdc[3].setY(posY + largeur / 2);
        }
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void selectionnerEtape(){
        estSelect = true;
    }

    public void deselectionnerEtape(){
        estSelect = false;
    }

    public boolean estSelectionnee(){
        return estSelect;
    }

    public boolean isUneEntree(){return estUneEntre;}
    public void setEtapeEntree(boolean bool){
        estUneEntre = bool;
    }

    public boolean isUneSortie(){return estUneSortie;}
    public void setEstUneSortie(boolean bool){
        estUneSortie = bool;
    }

    public void repositionnerPDC(){
        if(!this.isUnGuichet()){
            pdc[0].setX(posX + hauteur / 2);
            pdc[0].setY(posY);
            //Gauche
            pdc[1].setX(posX);
            pdc[1].setY(posY + (largeur / 2));
            //Bas
            pdc[2].setX(posX + hauteur / 2);
            pdc[2].setY(posY + largeur);
            //Droite
            pdc[3].setX(posX + hauteur);
            pdc[3].setY(posY + largeur / 2);
        }
        else{
            pdc[0].setX(posX);
            pdc[0].setY(posY + (largeur / 2));
            pdc[1].setX(posX + hauteur);
            pdc[1].setY(posY + largeur / 2);
        }
    }

    @Override
    public String toString() {
        return "EtapeIG{" +
                "nom='" + nom + '\'' +
                ", identifiant='" + identifiant + '\'' +
                ", posX=" + posX +
                ", posY=" + posY +
                ", largeur=" + largeur +
                ", hauteur=" + hauteur +
                ", \n pdc=" + Arrays.toString(pdc) +
                "}\n\n";
    }

    public String getNom() {
        return nom;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getLargeur() {
        return largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public void setDelai(int delai) {
        this.delai = delai;
    }

    public void setEcart(int ecart) {
        this.ecart = ecart;
    }

    public int getDelai() {
        return delai;
    }

    public int getEcart() {
        return ecart;
    }

    public PointDeControlIG getPdc(int lui){
        return pdc[lui];
    }

    public boolean isUnGuichet(){return false;}

    public abstract void changerNbJeton(int nouveau);

    public int getNbJetons(){return 0;}

    public void renommerEtapeIG(String newNom){
        System.out.println("Avant j'étais : " + this.nom);
        this.nom = newNom;
        System.out.println("Voici mon nouveau nom : " + newNom);
    }

}
