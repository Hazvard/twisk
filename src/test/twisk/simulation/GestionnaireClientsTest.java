package twisk.simulation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import twisk.monde.Activite;
import twisk.monde.Etape;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class GestionnaireClientsTest {

    private GestionnaireClients gstClients;
    private Client client1;
    private Client client2;

    @BeforeEach
    void setUp() {

        client1 = new Client(47);
        client2 = new Client(13);

        gstClients = new GestionnaireClients();
    }

    @Test
    void Constructeur1() {
        assertEquals(new HashMap<>(), gstClients.getListeClient());
    }

    @Test
    void setClients() {
        gstClients.setClients(client1.getNumClient(), client2.getNumClient());
        HashMap<Integer, Client> liste = gstClients.getListeClient();

        assertEquals( client1.getNumClient() , liste.get(47).getNumClient());
        assertEquals( client2.getNumClient() , liste.get(13).getNumClient());

    }

    @Test
    void nettoyer() {
        gstClients.setClients(client1.getNumClient(), client2.getNumClient());
        gstClients.nettoyer();

        assertEquals(new HashMap<Integer, Client>(), gstClients.getListeClient());
    }

    @Test
    void iterator() {

        int compteur = 0;
        gstClients.setClients(client1.getNumClient(), client2.getNumClient());

        for(Client client : gstClients){
            assertEquals(client.getRang(), client.getRang());
            compteur++;
        }

        assertEquals(2, compteur);
    }

    @Test
    void AllerA() {
        gstClients.setClients(client1.getNumClient(), client2.getNumClient());
        Etape etape = new Activite("Aller_à_la_maison");
        gstClients.allerA(47, etape, 3);

        HashMap<Integer, Client> liste = gstClients.getListeClient();

        assertEquals("Aller_à_la_maison", liste.get(47).getEtape().getNom());
        assertEquals(3, liste.get(47).getRang());
    }
}