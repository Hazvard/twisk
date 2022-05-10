package twiskIG.tests;

import twiskIG.mondeIG.MondeIG;
import org.junit.jupiter.api.BeforeEach;

public class TestMondeIG {
    MondeIG mondeIG;


    @BeforeEach
    void setUp(){
        mondeIG = new MondeIG();
    }

    @org.junit.jupiter.api.Test
    void testmondeIG(){
        System.out.println(mondeIG.toString());

        mondeIG.ajouter("Activite");
        System.out.println(mondeIG.toString());

        mondeIG.ajouter("Activite");
        System.out.println(mondeIG.toString());
    }
}
