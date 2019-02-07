package it.unisa.tophw.server.model.management;

import it.unisa.tophw.server.model.beans.CatalogBean;
import it.unisa.tophw.server.model.beans.ProductBean;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CatalogDAOTest {
    static CatalogDAO catalogDAO;

    @BeforeEach
    void setUp() {
        catalogDAO=new CatalogDAO();
        assertNotNull(catalogDAO);
    }

    @AfterEach
    void tearDown() {
        catalogDAO=null;
        assertNull(catalogDAO);
    }

    @Test
    void doRetriveByKey() {
        CatalogBean catalogBean = catalogDAO.doRetriveByKey(1);
        assertEquals(1,catalogBean.getId_catalogo());
    }

    @Test
    void doRetriveBynome() {
        CatalogBean catalogBean = catalogDAO.doRetriveBynome("Tastiera");
        assertEquals("Tastiera",catalogBean.getNomeCatalogo());
    }

    @Test
    void doSave() {
        CatalogBean catalogBean =  new CatalogBean(100,"newCatalog","newCatalog Desc",new ArrayList<ProductBean>(),0);
        catalogDAO.doSave(catalogBean);
    }


    @Test
    void doUpdate() {
        CatalogBean catalogBean= catalogDAO.doRetriveByKey(100);
        catalogBean.setNomeCatalogo("Nuovo nome catalogo");
        catalogDAO.doUpdate(catalogBean);
    }

    @Test
    void doDelete() {
        CatalogBean catalogBean = catalogDAO.doRetriveByKey(100);
        catalogDAO.doDelete(catalogBean.getId_catalogo());
    }

}