package it.unisa.tophw.server.model.beans;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductBeanTest {
protected ProductBean prodotto;
    @BeforeEach
    void setUp() {
        prodotto = new ProductBean(1,10,"prodotto","breve","estesa","a","b","c",1.0,1,1);
    }


    @Test
    void getId_prodotto() {
        int id_prodotto = prodotto.getId_prodotto();
        assertEquals(1,id_prodotto);
    }

    @Test
    void setId_prodotto() {
        prodotto.setId_prodotto(100);
        int id_prodotto = prodotto.getId_prodotto();
        assertEquals(100,id_prodotto);

    }

    @Test
    void getQuantita() {
        int qta = prodotto.getQuantita();
        assertEquals(10,qta);
    }

    @Test
    void setQuantita() {
        prodotto.setQuantita(1);
        int qta = prodotto.getQuantita();
        assertEquals(1,qta);

    }

    @Test
    void getNome() {
        String nome = prodotto.getNome();
        assertEquals("prodotto",nome);
    }

    @Test
    void setNome() {
        prodotto.setNome("prodottoTest");
        String nome = prodotto.getNome();
        assertEquals("prodottoTest",nome);

    }

    @Test
    void getDescrizione_breve() {
        String desc_breve = prodotto.getDescrizione_breve();
        assertEquals("breve",desc_breve);
    }

    @Test
    void setDescrizione_breve() {
        prodotto.setDescrizione_breve("test");
        String desc_breve = prodotto.getDescrizione_breve();
        assertEquals("test",desc_breve);

    }

    @Test
    void getDescrizione_estesa() {
        String desc_estesa = prodotto.getDescrizione_estesa();
        assertEquals("estesa", desc_estesa);
    }

    @Test
    void setDescrizione_estesa() {
        prodotto.setDescrizione_estesa("test");
        String desc_estesa = prodotto.getDescrizione_estesa();
        assertEquals("test", desc_estesa);

    }

    @Test
    void getPathImg1() {
        String path_1 = prodotto.getPathImg1();
        assertEquals("a",path_1);
    }

    @Test
    void setPathImg1() {
        prodotto.setPathImg1("abc");
        String path_1 = prodotto.getPathImg1();
        assertEquals("abc",path_1);

    }

    @Test
    void getPathImg2() {
        String path_2 = prodotto.getPathImg2();
        assertEquals("b",path_2);
    }

    @Test
    void setPathImg2() {
        prodotto.setPathImg2("abc");
        String path_2 = prodotto.getPathImg2();
        assertEquals("abc",path_2);
    }

    @Test
    void getPathImg3() {
        String path_3 = prodotto.getPathImg3();
        assertEquals("c",path_3);
    }

    @Test
    void setPathImg3() {
        prodotto.setPathImg3("abc");
        String path_3 = prodotto.getPathImg3();
        assertEquals("abc",path_3);
    }

    @Test
    void getPrezzo() {
        double prezzo = prodotto.getPrezzo();
        assertEquals(1.0,prezzo);
    }

    @Test
    void setPrezzo() {
        prodotto.setPrezzo(11.0);
        double prezzo = prodotto.getPrezzo();
        assertEquals(11.0,prezzo);

    }

    @Test
    void getId_marca() {
        int id_marca = prodotto.getId_marca();
        assertEquals(1,id_marca);
    }

    @Test
    void setId_marca() {
        prodotto.setId_marca(10);
        int id_marca = prodotto.getId_marca();
        assertEquals(10,id_marca);

    }

    @Test
    void getId_catalogo() {
        int id_catalogo = prodotto.getId_catalogo();
        assertEquals(1,id_catalogo);
    }

    @Test
    void setId_catalogo() {
        prodotto.setId_catalogo(100);
        int id_catalogo = prodotto.getId_catalogo();
        assertEquals(100,id_catalogo);

    }
}