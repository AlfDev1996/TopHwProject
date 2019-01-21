package it.unisa.tophw.server.model.beans;

public class UserBean {

    private int id_utente;
    private String nome,cognome,email,password,ruolo,nazione;
    private AddressBean indirizzo;

    public UserBean() {
        id_utente=0;
        nome=cognome=email=password=ruolo=nazione="";
        indirizzo=new AddressBean();
    }

    public UserBean(int id_utente, String nome, String cognome, String email, String password, String ruolo, String nazione, AddressBean indirizzo) {
        this.id_utente = id_utente;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.ruolo = ruolo;
        this.nazione = nazione;
        this.indirizzo=indirizzo;
    }


    public AddressBean getIndirizzo() {

        return indirizzo;
    }


    public void setIndirizzo(AddressBean indirizzo) {
        this.indirizzo = indirizzo;
    }


    public int getId_utente() {
        return id_utente;
    }

    public void setId_utente(int id_utente) {
        this.id_utente = id_utente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public String getNazione() {
        return nazione;
    }

    public void setNazione(String nazione) {
        this.nazione = nazione;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "id_utente=" + id_utente +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", ruolo='" + ruolo + '\'' +
                ", nazione='" + nazione + '\'' +
                '}';
    }
}
