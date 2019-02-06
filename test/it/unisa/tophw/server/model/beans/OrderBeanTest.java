package it.unisa.tophw.server.model.beans;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class OrderBeanTest {
    protected OrderBean ordine ;
    protected AddressBean indirizzo = new AddressBean();
    protected UserBean utente = new UserBean();
    @BeforeEach
    void setUp() {
        ordine = new OrderBean(1,new Date(2019,01,31),"creato","via milano",10,new ArrayList<OrderVoiceBean>(),utente,indirizzo, "paypal");
    }

    @Test
    void getId_ordine() {
        int id_ordine = ordine.getId_ordine();
        assertEquals(1,id_ordine);
    }

    @Test
    void setId_ordine() {
        ordine.setId_ordine(25);
        int id_ordine = ordine.getId_ordine();
        assertEquals(25,id_ordine);

    }

    @Test
    void getData_creazione() {
        Date data = ordine.getData_creazione();
        assertEquals(new Date(2019,01,31),data);
    }

    @Test
    void setData_creazione() {
        ordine.setData_creazione(new Date(2020,01,1));
        Date data = ordine.getData_creazione();
        assertEquals(new Date(2020,01,1),data);
    }

    @Test
    void getStato() {
        String stato_ordine=ordine.getStato();
        assertEquals("creato",stato_ordine);
    }

    @Test
    void setStato() {
        ordine.setStato("annullato");
        String stato_ordine=ordine.getStato();
        assertEquals("annullato",stato_ordine);

    }

    @Test
    void getTipoPagamento() {
        String tipo_pagamento = ordine.getTipoPagamento();
        assertEquals("paypal",tipo_pagamento);
    }

    @Test
    void setTipoPagamento() {
        ordine.setTipoPagamento("carta");
        String tipo_pagamento = ordine.getTipoPagamento();
        assertEquals("carta",tipo_pagamento);

    }

    @Test
    void getIndirizzoSpedizione() {
        AddressBean addr = ordine.getIndirizzoSpedizione();
        assertEquals(indirizzo, addr);
    }

    @Test
    void setIndirizzoSpedizione() {
         AddressBean addressTest = new AddressBean(1,123,1,"via roma","napoli","napoli","italia",1);
         ordine.setIndirizzoSpedizione(addressTest);
         assertEquals(addressTest,ordine.getIndirizzoSpedizione());
    }

    @Test
    void getTotale() {
        float totale = ordine.getTotale();
        assertEquals(10,totale);
    }

    @Test
    void setTotale() {
        ordine.setTotale(50);
        float totale = ordine.getTotale();
        assertEquals(50,totale);

    }

    @Test
    void getVociOrdine() {
        ArrayList<OrderVoiceBean> vociOrdine = ordine.getVociOrdine();
        assertEquals(new ArrayList<OrderVoiceBean>(),vociOrdine);
    }

    @Test
    void setVociOrdine() {
        ArrayList<OrderVoiceBean> vociOrdine = new ArrayList<OrderVoiceBean>();
        vociOrdine.add(new OrderVoiceBean());
        ordine.setVociOrdine(vociOrdine);
        assertEquals(vociOrdine,ordine.getVociOrdine());
    }

    @Test
    void getUtente() {
        UserBean user = ordine.getUtente();
        assertEquals(utente, user);
    }

    @Test
    void setUtente() {
        UserBean utente = new UserBean(1,"alfonso","rianna","mail","pass","Admin","Italia",null);
        ordine.setUtente(utente);
        assertEquals(utente,ordine.getUtente());
    }



}