package twisk.simulation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
        assertEquals(new ArrayList<Client>(), gstClients.getListeClient());
    }

    @Test
    void Constructeur2() {
        gstClients = new GestionnaireClients(13);

        assertEquals(new ArrayList<Client>(13).size(), gstClients.getListeClient().size() );
    }


    @Test
    void setClients() {
        gstClients.setClients(client1.getNumClient(), client2.getNumClient());
        ArrayList<Client> liste = gstClients.getListeClient();

        assertEquals( client1.getNumClient() , liste.get(0).getNumClient());
        assertEquals( client2.getNumClient() , liste.get(1).getNumClient());

    }

    @Test
    void setNbClient() {
    }

    @Test
    void nettoyer() {
    }

    @Test
    void iterator() {
    }
}