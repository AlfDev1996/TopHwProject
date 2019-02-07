package it.unisa.tophw.server.model.management;

import it.unisa.tophw.server.model.beans.OrderBean;
import it.unisa.tophw.server.model.beans.ProductBean;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class OrderDAOTest {

    static OrderDAO orderDAO;

    @BeforeEach
    void setUp() {
        orderDAO=new OrderDAO();
        assertNotNull(orderDAO);
    }

    @AfterEach
    void tearDown() {
        orderDAO=null;
        assertNull(orderDAO);
    }

    @Test
    void doRetriveById() {
        OrderBean orderBean = orderDAO.doRetriveById(1);
        assertEquals(1,orderBean.getId_ordine());
    }

    @Test
    void doDelete() {

        OrderBean orderBean = orderDAO.doRetriveById(100);
        if(orderBean!=null)
        orderDAO.doDelete(orderBean.getId_ordine());

    }
}