package it.unisa.tophw.server.model.management;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductDAOTest {

    static  ProductDAO productDAO;

    @Test
    void doRetriveById() {


    }

    @Test
    void doRetriveByNome() {
    }

    @Test
    void decreaseQuantityProduct() {
    }

    @Test
    void doSave() {
    }

    @Test
    void doDelete() {
    }

    @Test
    void doUpdate() {
    }

    @BeforeEach
    void setUp() {
        productDAO=new ProductDAO();
        assertNotNull(productDAO);
    }

    @AfterEach
    void tearDown() {
        productDAO=null;
        assertNull(productDAO);
    }
}