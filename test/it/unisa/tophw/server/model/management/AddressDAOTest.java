package it.unisa.tophw.server.model.management;

import it.unisa.tophw.server.model.beans.AddressBean;
import it.unisa.tophw.server.model.beans.UserBean;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.jws.soap.SOAPBinding;

import static org.junit.jupiter.api.Assertions.*;

class AddressDAOTest {
    static AddressDAO addressDAO;

    @BeforeEach
    void setUp() {
        addressDAO=new AddressDAO();
        assertNotNull(addressDAO);

    }

    @AfterEach
    void tearDown() {
        addressDAO=null;
        assertNull(addressDAO);
    }

    @Test
    void doRetriveByUser() {
        UserDAO userDAO=new UserDAO();

        UserBean userBean= userDAO.doRetriveById(5);
        AddressBean addressBean= addressDAO.doRetriveByUser(userBean);
        assertEquals(addressBean.getId_indirizzo(),userBean.getIndirizzo().getId_indirizzo());
    }


    @Test
    void doSave() {

        UserDAO userDAO=new UserDAO();
        UserBean userBean = userDAO.doRetriveById(1);

        if(userBean.getIndirizzo()!=null && userBean.getIndirizzo().getId_indirizzo()>0){
            doUpdate();
        }else{
            AddressBean addressBean = new AddressBean(100,12245,5,"via palermo","somma vesuviana","napoli","italia",1);
            addressDAO.doSave(addressBean);
        }



    }

    @Test
    void doUpdate() {
        UserDAO userDAO =new UserDAO();
        UserBean userBean=userDAO.doRetriveById(1);

        AddressBean addressBean = userBean.getIndirizzo();
        addressBean.setComune("Nuovo Comune");
        addressBean.setProvincia("Nuova Provincia");
        addressDAO.doUpdate(addressBean);
    }

    @Test
    void doDelete() {

        AddressBean addressBean= addressDAO.doRetriveByUser(new UserDAO().doRetriveById(1));
        addressDAO.doDelete(addressBean.getId_indirizzo());
    }
}