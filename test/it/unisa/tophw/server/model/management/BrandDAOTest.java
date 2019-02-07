package it.unisa.tophw.server.model.management;

import it.unisa.tophw.server.model.beans.BrandBean;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrandDAOTest {

    static BrandDAO brandDAO;
    @BeforeEach
    void setUp() {
        brandDAO=new BrandDAO();
        assertNotNull(brandDAO);
    }

    @AfterEach
    void tearDown() {
        brandDAO=null;
        assertNull(brandDAO);
    }

    @Test
    void doRetriveByKey() {
        BrandBean brandBean = brandDAO.doRetriveByKey(1);
        assertEquals(1,brandBean.getIdMarca());
    }

    @Test
    void doRetriveBynome() {
        BrandBean brandBean = brandDAO.doRetriveBynome("ASUS");
        assertEquals("ASUS",brandBean.getNome());
    }

    @Test
    void doSave() {
        BrandBean brandBean = new BrandBean(100,"newbrand");
        brandDAO.doSave(brandBean);
    }



    @Test
    void doUpdate() {
        BrandBean brandBean = brandDAO.doRetriveByKey(100);
        brandBean.setNome("new nome");
        brandDAO.doUpdate(brandBean);
    }

    @Test
    void doDelete() {
        BrandBean brandBean = brandDAO.doRetriveByKey(100);
        brandDAO.doDelete(brandBean.getIdMarca());

    }
}