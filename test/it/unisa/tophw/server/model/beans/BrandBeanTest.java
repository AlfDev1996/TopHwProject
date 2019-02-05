package it.unisa.tophw.server.model.beans;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrandBeanTest {
protected BrandBean brand;
    @BeforeEach
    void setUp() {
        brand = new BrandBean(1,"asus");
    }

    @Test
    void getIdMarca() {
        int id_marca = brand.getIdMarca();
        assertEquals(1,id_marca);
    }

    @Test
    void setIdMarca() {
        brand.setIdMarca(100);
        int id_marca = brand.getIdMarca();
        assertEquals(100,id_marca);

    }

    @Test
    void getNome() {
        String nome = brand.getNome();
        assertEquals("asus",nome);
    }

    @Test
    void setNome() {
        brand.setNome("samsung");
        String nome = brand.getNome();
        assertEquals("samsung",nome);

    }

}