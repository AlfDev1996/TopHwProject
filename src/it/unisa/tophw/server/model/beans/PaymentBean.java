package it.unisa.tophw.server.model.beans;

import java.util.Calendar;
import java.util.Date;

public class PaymentBean {

    int id_pagamento;
    String intestatario, scadenza;
    int cvv;
    long numero_carta;
    double importo;
    Date data_pagamento;
    int id_ordine;

    public PaymentBean(){
        id_pagamento=0;
        intestatario="";
        numero_carta=0;
        scadenza="";
        cvv=0;
        importo=0;
        data_pagamento= Calendar.getInstance().getTime();
        id_ordine=0;
    }

    public PaymentBean(int id_pagamento, String intestatario, String scadenza, int cvv, long numero_carta, double importo, Date data_pagamento, int id_ordine) {
        this.id_pagamento = id_pagamento;
        this.intestatario = intestatario;
        this.scadenza = scadenza;
        this.cvv = cvv;
        this.numero_carta = numero_carta;
        this.importo = importo;
        this.data_pagamento = data_pagamento;
        this.id_ordine = id_ordine;
    }

    public int getId_ordine() {
        return id_ordine;
    }

    public void setId_ordine(int id_ordine) {
        this.id_ordine = id_ordine;
    }

    public long getNumero_carta() {
        return numero_carta;
    }

    public void setNumero_carta(long numero_carta) {
        this.numero_carta = numero_carta;
    }

    public double getImporto() {
        return importo;
    }

    public void setImporto(double importo) {
        this.importo = importo;
    }

    public Date getData_pagamento() {
        return data_pagamento;
    }

    public void setData_pagamento(Date data_pagamento) {
        this.data_pagamento = data_pagamento;
    }

    public int getId_pagamento() {
        return id_pagamento;
    }

    public void setId_pagamento(int id_pagamento) {
        this.id_pagamento = id_pagamento;
    }

    public String getIntestatario() {
        return intestatario;
    }

    public void setIntestatario(String intestatario) {
        this.intestatario = intestatario;
    }



    public String getScadenza() {
        return scadenza;
    }

    public void setScadenza(String scadenza) {
        this.scadenza = scadenza;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }


}
