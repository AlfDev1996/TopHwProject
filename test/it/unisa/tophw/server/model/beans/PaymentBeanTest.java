package it.unisa.tophw.server.model.beans;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PaymentBeanTest {
    protected PaymentBean pay;
    @BeforeEach
    void setUp() {
        pay = new PaymentBean(1,"alfonso","10-11-12",222,123456789,10.0,new Date(2002,12,2),3);
    }

    @Test
    void getId_ordine() {
        int id_ordine = pay.getId_ordine();
        assertEquals(3,id_ordine);
    }

    @Test
    void setId_ordine() {
        pay.setId_ordine(100);
        int id_ordine = pay.getId_ordine();
        assertEquals(100,id_ordine);

    }

    @Test
    void getNumero_carta() {
        long num_carta = pay.getNumero_carta();
        assertEquals(123456789,num_carta);
    }

    @Test
    void setNumero_carta() {
    pay.setNumero_carta(123456788);
        long num_carta = pay.getNumero_carta();
        assertEquals(123456788,num_carta);

    }

    @Test
    void getImporto() {
        double importo =pay.getImporto();
        assertEquals(10.0,importo);
    }

    @Test
    void setImporto() {
        pay.setImporto(11.0);
        double importo =pay.getImporto();
        assertEquals(11.0,importo);

    }

    @Test
    void getData_pagamento() {
        Date data = pay.getData_pagamento();
        assertEquals(new Date(2002,12,2),data);
    }

    @Test
    void setData_pagamento() {
        pay.setData_pagamento(new Date(2009,12,1));
        Date data = pay.getData_pagamento();
        assertEquals(new Date(2009,12,1),data);
    }

    @Test
    void getId_pagamento() {
        int id_pagamento =pay.getId_pagamento();
        assertEquals(1,id_pagamento);
    }

    @Test
    void setId_pagamento() {
        pay.setId_pagamento(101);
        int id_pagamento =pay.getId_pagamento();
        assertEquals(101,id_pagamento);

    }

    @Test
    void getIntestatario() {
        String intestatario =pay.getIntestatario();
        assertEquals("alfonso",intestatario);
    }

    @Test
    void setIntestatario() {
        pay.setIntestatario("raffaele");
        String intestatario =pay.getIntestatario();
        assertEquals("raffaele",intestatario);

    }

    @Test
    void getScadenza() {
        String scadenza= pay.getScadenza();
        assertEquals("10-11-12",scadenza);
    }

    @Test
    void setScadenza() {
        pay.setScadenza("15-12-2020");
        String scadenza= pay.getScadenza();
        assertEquals("15-12-2020",scadenza);

    }

    @Test
    void getCvv() {
        int cvv= pay.getCvv();
        assertEquals(222,cvv);
    }

    @Test
    void setCvv() {
        pay.setCvv(333);
        int cvv= pay.getCvv();
        assertEquals(333,cvv);

    }
}