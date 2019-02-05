package it.unisa.tophw.server.model.beans;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserBeanTest {

    protected UserBean userBean;
    @BeforeEach
    void setUp() {
        //UserBean(int id_utente, String nome, String cognome, String email, String password, String ruolo, String nazione, AddressBean indirizzo)
        // AddressBean(int id_indirizzo, int cap, int civico, String via, String comune, String provincia, String nazione, int id_utente)
        this.userBean = new UserBean(100,"raffaele","dragone","em@gmail.com","password","Admin","Italia",new AddressBean(100,80030,28,"Palermo","Mariglianella","Napoli","Italia",100));
    }



    @Test
    void getId_utente() {
        int id = this.userBean.getId_utente();
        assertEquals(100,id);

    }

    @Test
    void setId_utente() {
        this.userBean.setId_utente(101);
        int id = this.userBean.getId_utente();
        assertEquals(101,id);

    }

    @Test
    void getNome() {
        String nome = this.userBean.getNome();
        assertEquals("raffaele",nome);
    }


    @Test
    void setNome() {
        this.userBean.setNome("Alfonso");
        String nome = this.userBean.getNome();
        assertEquals("Alfonso",nome);
    }

    @Test
    void getCognome() {
        String cognome = this.userBean.getCognome();
        assertEquals("dragone",cognome);
    }

    @Test
    void setCognome() {
        this.userBean.setCognome("Rianna");
        String cognome = this.userBean.getCognome();
        assertEquals("Rianna",cognome);
    }

    @Test
    void getEmail() {
        String email = this.userBean.getEmail();
        assertEquals("em@gmail.com",email);
    }

    @Test
    void setEmail() {
        this.userBean.setEmail("alfonso@gmail.com");
        String email = this.userBean.getEmail();
        assertEquals("alfonso@gmail.com",email);
    }

    @Test
    void getPassword() {
        String password = this.userBean.getPassword();
        assertEquals("password",password);
    }

    @Test
    void setPassword() {
        this.userBean.setPassword("newpassword");
        String password = this.userBean.getPassword();
        assertEquals("newpassword",password);
    }

    @Test
    void getRuolo() {
        String ruolo = this.userBean.getRuolo();
        assertEquals("Admin",ruolo);
    }

    @Test
    void setRuolo() {
        this.userBean.setRuolo("User");
        String ruolo = this.userBean.getRuolo();
        assertEquals("User",ruolo);
    }

    @Test
    void getNazione() {
        String nazione = this.userBean.getNazione();
        assertEquals("Italia",nazione);
    }

    @Test
    void setNazione() {
        this.userBean.setNazione("Brasile");
        String nazione = this.userBean.getNazione();
        assertEquals("Brasile",nazione);
    }
}