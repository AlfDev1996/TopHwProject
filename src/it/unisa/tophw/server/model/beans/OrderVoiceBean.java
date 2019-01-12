package it.unisa.tophw.server.model.beans;

public class OrderVoiceBean {

    private OrderBean ordine ;
    private ProductBean prodotto;
    private double prezzo_unitario, prezzo_totale,valore_sconto;
    private int quantita, id_voce_ordine;

    public OrderVoiceBean(OrderBean ordine, ProductBean prodotto, double prezzo_unitario, double prezzo_totale, double valore_sconto, int quantita, int id_voce_ordine) {
        this.ordine = ordine;
        this.prodotto = prodotto;
        this.prezzo_unitario = prezzo_unitario;
        this.prezzo_totale = prezzo_totale;
        this.valore_sconto = valore_sconto;
        this.quantita = quantita;
        this.id_voce_ordine = id_voce_ordine;
    }

    public OrderVoiceBean() {
        id_voce_ordine=0;
        ordine=new OrderBean();
        prodotto=new ProductBean();
        prezzo_unitario=0.0;
        prezzo_totale=0.0;
        valore_sconto=0.0;
        quantita=0;
    }

    public OrderBean getOrdine() {
        return ordine;
    }

    public void setOrdine(OrderBean ordine) {
        this.ordine = ordine;
    }

    public ProductBean getProdotto() {
        return prodotto;
    }

    public void setProdotto(ProductBean prodotto) {
        this.prodotto = prodotto;
    }

    public double getPrezzo_unitario() {
        return prezzo_unitario;
    }

    public void setPrezzo_unitario(double prezzo_unitario) {
        this.prezzo_unitario = prezzo_unitario;
    }

    public double getPrezzo_totale() {
        return prezzo_totale;
    }

    public void setPrezzo_totale(double prezzo_totale) {
        this.prezzo_totale = prezzo_totale;
    }

    public double getValore_sconto() {
        return valore_sconto;
    }

    public void setValore_sconto(double valore_sconto) {
        this.valore_sconto = valore_sconto;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public int getId_voce_ordine() {
        return id_voce_ordine;
    }

    public void setId_voce_ordine(int id_voce_ordine) {
        this.id_voce_ordine = id_voce_ordine;
    }

    @Override
    public String toString() {
        return "OrderVoiceBean{" +
                "ordine=" + ordine +
                ", prodotto=" + prodotto +
                ", prezzo_unitario=" + prezzo_unitario +
                ", prezzo_totale=" + prezzo_totale +
                ", valore_sconto=" + valore_sconto +
                ", quantita=" + quantita +
                ", id_voce_ordine=" + id_voce_ordine +
                '}';
    }
}
