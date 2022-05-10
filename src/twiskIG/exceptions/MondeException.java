package twiskIG.exceptions;

public class MondeException extends Exception {
    String leText;

    public MondeException(String text){
        leText = text;
    }

    public String getText(){
        return leText;
    }
}
