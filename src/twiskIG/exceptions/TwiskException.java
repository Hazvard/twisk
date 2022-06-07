package twiskIG.exceptions;


public class TwiskException extends Exception{
    String leText;

    /**
     * Constructeur
     * @param text
     */
    public TwiskException(String text){
        leText = text;
    }

    /**
     * Renvoie le texte de l'erreur
     * @return
     */
    public String getText(){
        return leText;
    }
}
