package twiskIG.exceptions;


public class TwiskException extends Exception{
    String leText;

    public TwiskException(String text){
        leText = text;
    }

    public String getText(){
        return leText;
    }
}
