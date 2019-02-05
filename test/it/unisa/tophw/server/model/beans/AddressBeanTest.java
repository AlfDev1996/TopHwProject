package it.unisa.tophw.server.model.beans;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressBeanTest {
    protected AddressBean address;
    @BeforeEach
    void setUp() {
        this.address= new AddressBean(100,12245,5,"via palermo","somma vesuviana","napoli","italia",1);
    }

    @Test
    void getId_utente() {
        int id_utente = address.getId_utente();
        assertEquals(1,id_utente);
    }

    @Test
    void setId_utente() {
        address.setId_utente(2);
        int id_utente = address.getId_utente();
        assertEquals(2,id_utente);

    }

    @Test
    void getId_indirizzo() {
        int id_indirizzo = address.getId_indirizzo();
        assertEquals(100,id_indirizzo);
    }

    @Test
    void setId_indirizzo() {
        address.setId_indirizzo(3);
        int id_indirizzo = address.getId_indirizzo();
        assertEquals(3,id_indirizzo);


    }

    @Test
    void getCap() {
        int cap = address.getCap();
        assertEquals(12245,cap);
    }

    @Test
    void setCap() {
        address.setCap(1000);
        int cap = address.getCap();
        assertEquals(1000,cap);

    }

    @Test
    void getCivico() {
        int civico = address.getCivico();
        assertEquals(5,civico);
    }

    @Test
    void setCivico() {
        address.setCivico(10);
        int civico = address.getCivico();
        assertEquals(10,civico);

    }

    @Test
    void getVia() {
        String via = address.getVia();
        assertEquals("via palermo", via);
    }

    @Test
    void setVia() {
        address.setVia("via1");
        String via = address.getVia();
        assertEquals("via1", via);
    }

    @Test
    void getComune() {
        String comune = address.getComune();
        assertEquals("somma vesuviana", comune);
    }

    @Test
    void setComune() {
        address.setComune("mariglianella");
        String comune = address.getComune();
        assertEquals("mariglianella", comune);

    }

    @Test
    void getProvincia() {
        String proviancia = address.getProvincia();
        assertEquals("napoli",proviancia);

    }

    @Test
    void setProvincia() {
        address.setProvincia("roma");
        String proviancia = address.getProvincia();
        assertEquals("roma",proviancia);

    }

    @Test
    void getNazione() {
        String nazione = address.getNazione();
        assertEquals("italia",nazione);
    }

    @Test
    void setNazione() {
        address.setNazione("germania");
        String nazione = address.getNazione();
        assertEquals("germania",nazione);

    }


}