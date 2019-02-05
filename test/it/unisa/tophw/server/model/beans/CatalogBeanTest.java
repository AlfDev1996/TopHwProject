package it.unisa.tophw.server.model.beans;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CatalogBeanTest {
    protected CatalogBean catalog;
    @BeforeEach
    void setUp() {
        catalog = new CatalogBean(1,"catalogoTest","descrizione",new ArrayList<ProductBean>(),0);
    }

    @Test
    void getId_catalogo() {
        int id_catalogo = catalog.getId_catalogo();
        assertEquals(1,id_catalogo);
    }

    @Test
    void setId_catalogo() {
        catalog.setId_catalogo(100);
        int id_catalogo = catalog.getId_catalogo();
        assertEquals(100,id_catalogo);

    }

    @Test
    void getNomeCatalogo() {
        String nome_catalogo = catalog.getNomeCatalogo();
        assertEquals("catalogoTest",nome_catalogo);
    }

    @Test
    void setNomeCatalogo() {
        catalog.setNomeCatalogo("catalogo");
        String nome_catalogo = catalog.getNomeCatalogo();
        assertEquals("catalogo",nome_catalogo);

    }

    @Test
    void getDescrizioneCatalogo() {
        String descrizione_catalogo = catalog.getDescrizioneCatalogo();
        assertEquals("descrizione",descrizione_catalogo);
    }

    @Test
    void setDescrizioneCatalogo() {
        catalog.setDescrizioneCatalogo("prova");
        String descrizione_catalogo = catalog.getDescrizioneCatalogo();
        assertEquals("prova",descrizione_catalogo);
    }

    @Test
    void getProdottiCatalogo() {
        assertEquals(new ArrayList<ProductBean>(), catalog.getProdottiCatalogo());
    }

    @Test
    void setProdottiCatalogo() {
        ArrayList<ProductBean> prodotti = new ArrayList<>();
        ProductBean prodottoTest = new ProductBean(1,10,"monitor","testdesc","testdescestesa","a","b","c",10.0,1,2);
        prodotti.add(prodottoTest);
        catalog.setProdottiCatalogo(prodotti);
        assertEquals(prodotti,catalog.getProdottiCatalogo());
    }

    @Test
    void getSconto() {
        int sconto = catalog.getSconto();
        assertEquals(0,sconto);
    }

    @Test
    void setSconto() {
        catalog.setSconto(50);
        int sconto = catalog.getSconto();
        assertEquals(50,sconto);

    }

    @Test
    void addProduct() {
        ProductBean prodottoTest = new ProductBean(1,10,"monitor","testdesc","testdescestesa","a","b","c",10.0,1,2);
        catalog.addProduct(prodottoTest);
        assertEquals(prodottoTest,catalog.getProdottiCatalogo().get(0));
    }

    @Test
    void deleteProduct() {
        ProductBean prodottoTest = new ProductBean(1,10,"monitor","testdesc","testdescestesa","a","b","c",10.0,1,2);
        catalog.addProduct(prodottoTest);
        catalog.deleteProduct(prodottoTest);
        assertEquals(new ArrayList<ProductBean>(),catalog.getProdottiCatalogo());
    }
}