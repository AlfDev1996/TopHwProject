package it.unisa.tophw.server.model.beans;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderVoiceBeanTest {
protected OrderVoiceBean voceOrdine;
protected OrderBean ordine  = new OrderBean();
protected ProductBean prodotto = new ProductBean();
    @BeforeEach
    void setUp() {
        voceOrdine = new OrderVoiceBean(ordine,prodotto,10.0,20.0,10,1,1);
    }

    @Test
    void getOrdine() {
        OrderBean ordineTest = voceOrdine.getOrdine();
        assertEquals(ordine,ordineTest);
    }

    @Test
    void setOrdine() {
        OrderBean orderTest = new OrderBean();
        voceOrdine.setOrdine(orderTest);
        assertEquals(orderTest,voceOrdine.getOrdine());
    }

    @Test
    void getProdotto() {
        ProductBean prod = voceOrdine.getProdotto();
        assertEquals(prodotto,prod);
    }

    @Test
    void setProdotto() {
        ProductBean prodTest = new ProductBean();
        voceOrdine.setProdotto(prodTest);
        assertEquals(prodTest,voceOrdine.getProdotto());
    }

    @Test
    void getPrezzo_unitario() {
        double prezzo_unitario = voceOrdine.getPrezzo_unitario();
        assertEquals(10.0,prezzo_unitario);
    }

    @Test
    void setPrezzo_unitario() {
        voceOrdine.setPrezzo_unitario(11);
        double prezzo_unitario = voceOrdine.getPrezzo_unitario();
        assertEquals(11,prezzo_unitario);

    }

    @Test
    void getPrezzo_totale() {
        double prezzo_totale = voceOrdine.getPrezzo_totale();
        assertEquals(20.0,prezzo_totale);
    }

    @Test
    void setPrezzo_totale() {
        voceOrdine.setPrezzo_totale(100);
        double prezzo_totale = voceOrdine.getPrezzo_totale();
        assertEquals(100,prezzo_totale);

    }

    @Test
    void getValore_sconto() {
        double valore_sconto = voceOrdine.getValore_sconto();
        assertEquals(10,valore_sconto);
    }

    @Test
    void setValore_sconto() {
        voceOrdine.setValore_sconto(100);
        double valore_sconto = voceOrdine.getValore_sconto();
        assertEquals(100,valore_sconto);
    }

    @Test
    void getQuantita() {
        int quantita = voceOrdine.getQuantita();
        assertEquals(1,quantita);
    }

    @Test
    void setQuantita() {
        voceOrdine.setQuantita(100);
        int quantita = voceOrdine.getQuantita();
        assertEquals(100,quantita);

    }

    @Test
    void getId_voce_ordine() {
        int id_voce_ordine = voceOrdine.getId_voce_ordine();
        assertEquals(1,id_voce_ordine);
    }

    @Test
    void setId_voce_ordine() {
        voceOrdine.setId_voce_ordine(100);
        int id_voce_ordine = voceOrdine.getId_voce_ordine();
        assertEquals(100,id_voce_ordine);

    }
}