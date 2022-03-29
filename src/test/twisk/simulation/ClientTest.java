package twisk.simulation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.monde.Activite;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    static private Client client;

    @BeforeEach
    void setUp() {
        client = new Client(47);
    }

    @Test
    void Constructeur1(){
        assertEquals(47, client.getNumClient());
    }

    @Test
    void allerA() {
        Activite activite = new Activite("Piste_de_dance");
        client.allerA(activite, 13);

        assertEquals(13, client.getRang());
        assertEquals("Piste_de_dance", client.getEtape().getNom());
    }
}