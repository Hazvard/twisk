package twiskIG.exceptions;

public class MondeException extends Exception {
    String leText;

    /**
     * Constructeur
     * @param text
     */
    public MondeException(String text){
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
