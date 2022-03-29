package twisk.simulation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        HashMap<Client> liste = gstClients.getListeClient();

        assertEquals( client1.getNumClient() , liste.get(0).getNumClient());
        assertEquals( client2.getNumClient() , liste.get(1).getNumClient());

    }

    @Test
    void nettoyer() {
        gstClients.setClients(client1.getNumClient(), client2.getNumClient());
        gstClients.nettoyer();

        assertEquals(new HashMap<Client>(), gstClients.getListeClient());
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

    }
}