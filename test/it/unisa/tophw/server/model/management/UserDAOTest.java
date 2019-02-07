package it.unisa.tophw.server.model.management;

import it.unisa.tophw.server.model.beans.UserBean;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {
    static UserDAO userDao;

    @BeforeEach
    void setUp() {
        userDao=new UserDAO();
        assertNotNull(userDao);
    }

    @AfterEach
    void tearDown() {
        userDao=null;
        assertNull(userDao);
    }

    @Test
    void doRetriveByEmailAndPassword() {
        UserBean userBean = userDao.doRetriveByEmailAndPassword("raffidragone@gmail.com","ciao");
        assertEquals(userBean.getEmail(),"raffidragone@gmail.com");
    }

    @Test
    void doRetriveByEmail() {
        UserBean userBean = userDao.doRetriveByEmail("raffidragone@gmail.com");
        assertEquals(userBean.getEmail(),"raffidragone@gmail.com");
    }


    @Test
    void doRetriveById() {
        UserBean userBean = userDao.doRetriveById(1);
        assertEquals(1,userBean.getId_utente());
    }

    @Test
    void doSave() {
        UserBean userBean=new UserBean();
        userBean.setNome("New Nome");
        userBean.setCognome("New Cognome");
        userBean.setNazione("Italia");
        userBean.setRuolo("Admin");
        userBean.setEmail("new@gmail.com");
        userBean.setPassword("pass");
        userBean.setId_utente(100);
        userBean.setIndirizzo(null);

        userDao.doSave(userBean);
    }




    @Test
    void doDelete() {
       UserBean userBean = userDao.doRetriveByEmailAndPassword("new@gmail.com","pass");
       userDao.doDelete(userBean.getId_utente());
    }
}