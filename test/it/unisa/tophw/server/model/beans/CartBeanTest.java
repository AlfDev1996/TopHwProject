package it.unisa.tophw.server.model.beans;

import it.unisa.tophw.server.model.management.ProductDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CartBeanTest {
protected CartBean cart;
    @BeforeEach
    void setUp() {
        cart= new CartBean(new UserBean(1,"alfonso","rianna","aaa@gmail.com","pass","Admin","italia",
                new AddressBean(1,80049,4,"roma","somma","napoli","italia",1)), new ArrayList<ProductBean>());
    }

    @Test
    void getUtente() {
        UserBean utente = cart.getUtente();
        UserBean user = new UserBean(1,"alfonso","rianna","aaa@gmail.com","pass","Admin","italia",
                new AddressBean(1,80049,4,"roma","somma","napoli","italia",1));
        assertEquals(user,utente);
    }

    @Test
    void setUtente() {
        cart.setUtente(new UserBean(1,"alfonso","dragone","aaa@gmail.com","pass","Admin","italia",
                new AddressBean(1,80049,4,"roma","somma","napoli","italia",1)));
        UserBean utente = cart.getUtente();

        UserBean user = new UserBean(1,"alfonso","dragone","aaa@gmail.com","pass","Admin","italia",
                new AddressBean(1,80049,4,"roma","somma","napoli","italia",1));
        assertEquals(user,utente);

    }

    @Test
    void getProdotti() {
        ArrayList<ProductBean> prodotti = cart.getProdotti();
        assertEquals(new ArrayList<ProductBean>(),prodotti);
    }

    @Test
    void setProdotti() {
        ProductBean prodottoTest = new ProductBean(1,10,"monitor","testdesc","testdescestesa","a","b","c",10.0,1,2);
        cart.addProduct(prodottoTest);

        ArrayList<ProductBean> prodotti = cart.getProdotti();
        assertEquals(cart.getProdotti().get(0),prodottoTest);

    }

    @Test
    void getPrezzoTotale() {
        double prezzo = cart.getPrezzoTotale();
        assertEquals(0.0, prezzo);
    }

    @Test
    void setPrezzoTotale() {
        cart.setPrezzoTotale(100);
        double prezzo = cart.getPrezzoTotale();
        assertEquals(100, prezzo);

    }

    @Test
    void addProduct() {
        ProductBean prodottoTest = new ProductBean(1,10,"monitor","testdesc","testdescestesa","a","b","c",10.0,1,2);

        cart.addProduct(prodottoTest);
        assertEquals(1,cart.getProdotti().size());

    }

    @Test
    void calcolaPrezzoCarrello() {
        double prezzo_totale= cart.getPrezzoTotale();
        assertEquals(0,prezzo_totale);
    }



    @Test
    void updateProdotto() {
        ProductBean prodottoTest = new ProductBean(1,10,"monitor","testdesc","testdescestesa","a","b","c",10.0,1,2);
        cart.addProduct(prodottoTest);
        ProductBean prodottoTest1 = new ProductBean(1,1,"monitor","testdesc","testdescestesa","a","b","c",10.0,1,2);
        cart.updateProdotto(1,prodottoTest1);

        assertEquals(prodottoTest1,cart.getProdotti().get(0));

    }

    @Test
    void deleteProdotto() {
        ProductBean prodottoTest = new ProductBean(1,10,"monitor","testdesc","testdescestesa","a","b","c",10.0,1,2);
        cart.addProduct(prodottoTest);
        cart.deleteProdotto(prodottoTest);
        assertEquals(new ArrayList<ProductBean>(),cart.getProdotti());
    }

    @Test
    void deleteAllProducts() {
        ProductBean prodottoTest = new ProductBean(1,10,"monitor","testdesc","testdescestesa","a","b","c",10.0,1,2);
        cart.addProduct(prodottoTest);
        cart.deleteAllProducts();
        assertEquals(new ArrayList<ProductBean>(),cart.getProdotti());

    }
}