package it.unisa.tophw.server.model.beans;

public class AddressBean {

    private int id_indirizzo;
    private int cap, civico;
    private String via, citta, provincia, nazione;

    public AddressBean(int id_indirizzo, int cap, int civico, String via, String citta, String provincia, String nazione) {
        this.id_indirizzo = id_indirizzo;
        this.cap = cap;
        this.civico = civico;
        this.via = via;
        this.citta = citta;
        this.provincia = provincia;
        this.nazione = nazione;
    }

    public AddressBean() {
        this.id_indirizzo = 0;
        this.cap = 0;
        this.civico = 0;
        this.via = "";
        this.citta = "";
        this.provincia = "";
        this.nazione = "";
    }

    public int getId_indirizzo() {
        return id_indirizzo;
    }

    public void setId_indirizzo(int id_indirizzo) {
        this.id_indirizzo = id_indirizzo;
    }

    public int getCap() {
        return cap;
    }

    public void setCap(int cap) {
        this.cap = cap;
    }

    public int getCivico() {
        return civico;
    }

    public void setCivico(int civico) {
        this.civico = civico;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getNazione() {
        return nazione;
    }

    public void setNazione(String nazione) {
        this.nazione = nazione;
    }

    @Override
    public String toString() {
        return "AddressBean{" +
                "id_indirizzo=" + id_indirizzo +
                ", cap=" + cap +
                ", civico=" + civico +
                ", via='" + via + '\'' +
                ", citta='" + citta + '\'' +
                ", provincia='" + provincia + '\'' +
                ", nazione='" + nazione + '\'' +
                '}';
    }
}
