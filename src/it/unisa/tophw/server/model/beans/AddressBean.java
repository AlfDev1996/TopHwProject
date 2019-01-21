package it.unisa.tophw.server.model.beans;

public class AddressBean {

    private int id_indirizzo;
    private int cap, civico;
    private String via, comune, provincia, nazione;
    private int id_utente;

    public AddressBean(int id_indirizzo, int cap, int civico, String via, String comune, String provincia, String nazione, int id_utente) {
        this.id_indirizzo = id_indirizzo;
        this.cap = cap;
        this.civico = civico;
        this.via = via;
        this.comune = comune;
        this.provincia = provincia;
        this.nazione = nazione;
        this.id_utente = id_utente;
    }

    public AddressBean() {
        this.id_indirizzo = 0;
        this.cap = 0;
        this.civico = 0;
        this.via = "";
        this.comune = "";
        this.provincia = "";
        this.nazione = "";
        this.id_utente=0;
    }

    public int getId_utente() {
        return id_utente;
    }

    public void setId_utente(int id_utente) {
        this.id_utente = id_utente;
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

    public String getComune() {
        return comune;
    }

    public void setComune(String comune) {
        this.comune = comune;
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
                ", comune='" + comune + '\'' +
                ", provincia='" + provincia + '\'' +
                ", nazione='" + nazione + '\'' +
                '}';
    }
}
