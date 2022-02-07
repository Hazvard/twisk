package test.monde;
import monde.*;
import org.junit.jupiter.api.BeforeEach;

import monde.Activite;
import monde.ActiviteRestreinte;
import monde.Etape;
import monde.SasSortie;

public class Etapetest {

    static private Etape etape ;

    public static void main(String[] args) {
        etape = new SasSortie();
        System.out.println(etape.toString());
    }

}
